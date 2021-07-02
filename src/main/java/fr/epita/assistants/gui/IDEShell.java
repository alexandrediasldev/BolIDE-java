package fr.epita.assistants.gui;

import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.*;

public class IDEShell extends JPanel {

    private JTextArea text;

    public IDEShell()
    {
        text = new JTextArea();
        Font font = new Font("ARIAL", Font.PLAIN, 24);
        text.setFont(font);
        text.setPreferredSize(new Dimension(1000,200));
        text.setForeground(Color.lightGray);
        text.setBackground(Color.darkGray);
        //add(text);
        add(new ConsolePane());
    }
    public void switchTheme() {
        if (!IDEConfig.INSTANCE.getDarkMode()) {
            text.setBackground(Color.lightGray);
            text.setForeground(Color.darkGray);

        } else {
            text.setForeground(Color.lightGray);
            text.setBackground(Color.darkGray);

        }
    }


}
interface CommandListener {

    public void commandOutput(String text);

    public void commandCompleted(String cmd, int result);

    public void commandFailed(Exception exp);
}

class ConsolePane extends JPanel implements CommandListener, Terminal {

    private JTextArea textArea;
    private int userInputStart = 0;
    private Command cmd;

    public ConsolePane() {


        cmd = new Command(this);

        setLayout(new BorderLayout());
        textArea = new JTextArea(20, 30);

        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new ProtectedDocumentFilter(this));
        add(new JScrollPane(textArea));

        InputMap im = textArea.getInputMap(WHEN_FOCUSED);
        ActionMap am = textArea.getActionMap();

        Action oldAction = am.get("insert-break");
        am.put("insert-break",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int range = textArea.getCaretPosition() - userInputStart;
                        try {
                            String text = textArea.getText(userInputStart, range).trim();
                            System.out.println("[" + text + "]");
                            userInputStart += range;
                            if (!cmd.isRunning()) {
                                cmd.execute(text);
                            } else {
                                try {

                                    cmd.send(text + "\n");
                                } catch (IOException ex) {
                                    appendText("!! Failed to send command to process: " + ex.getMessage() + "\n");
                                }
                            }
                        } catch (BadLocationException ex) {

                        }

                    oldAction.actionPerformed(e);
                }
    });
}

    @Override
    public void commandOutput(String text) {
        SwingUtilities.invokeLater(new AppendTask(this, text));
    }

    @Override
    public void commandFailed(Exception exp) {
        SwingUtilities.invokeLater(new AppendTask(this, "Command failed - " + exp.getMessage()));
    }

    @Override
    public void commandCompleted(String cmd, int result) {

        appendText("\n> " + cmd + " exited with " + result + "\n");
        appendText("\n");
    }

    protected void updateUserInputPos() {
        int pos = textArea.getCaretPosition();
        textArea.setCaretPosition(textArea.getText().length());
        userInputStart = pos;

    }

    @Override
    public int getUserInputStart() {
        return userInputStart;
    }

    @Override
    public void appendText(String text) {
        textArea.append(text);
        updateUserInputPos();
    }
}

interface UserInput {

    public int getUserInputStart();
}

interface Terminal extends UserInput {
    public void appendText(String text);
}

class AppendTask implements Runnable {

    private Terminal terminal;
    private String text;

    public AppendTask(Terminal textArea, String text) {
        this.terminal = textArea;
        this.text = text;
    }

    @Override
    public void run() {
        terminal.appendText(text);
    }
}

class Command {

    private CommandListener listener;
    private ProcessRunner runner;

    public Command(CommandListener listener) {
        this.listener = listener;
    }

    public boolean isRunning() {

        return runner != null && runner.isAlive();

    }

    public void execute(String cmd) {

        if (!cmd.trim().isEmpty()) {

            List<String> values = new ArrayList<>(25);
            if(System.getProperty("os.name").startsWith("Windows"))
                cmd = "powershell " + cmd;
            if (cmd.contains("\"")) {

                while (cmd.contains("\"")) {

                    String start = cmd.substring(0, cmd.indexOf("\""));
                    cmd = cmd.substring(start.length());
                    String quote = cmd.substring(cmd.indexOf("\"") + 1);
                    cmd = cmd.substring(cmd.indexOf("\"") + 1);
                    quote = quote.substring(0, cmd.indexOf("\""));
                    cmd = cmd.substring(cmd.indexOf("\"") + 1);

                    if (!start.trim().isEmpty()) {
                        String parts[] = start.trim().split(" ");
                        values.addAll(Arrays.asList(parts));
                    }
                    values.add(quote.trim());

                }

                if (!cmd.trim().isEmpty()) {
                    String parts[] = cmd.trim().split(" ");
                    values.addAll(Arrays.asList(parts));
                }

                for (String value : values) {
                    System.out.println("[" + value + "]");
                }

            } else {

                if (!cmd.trim().isEmpty()) {
                    String parts[] = cmd.trim().split(" ");
                    values.addAll(Arrays.asList(parts));
                }

            }

            runner = new ProcessRunner(listener, values);

        }

    }

    public void send(String cmd) throws IOException {
        runner.write(cmd);
    }
}

class ProcessRunner extends Thread {

    private List<String> cmds;
    private CommandListener listener;

    private Process process;

    public ProcessRunner(CommandListener listener, List<String> cmds) {
        this.cmds = cmds;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("cmds = " + cmds);
            ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.redirectErrorStream();
            process = pb.start();

            StreamReader reader = new StreamReader(listener, process.getInputStream());
            // Need a stream writer...

            int result = process.waitFor();

            // Terminate the stream writer
            reader.join();

            StringJoiner sj = new StringJoiner(" ");

            cmds.stream().forEach((cmd) -> {
                sj.add(cmd);
            });


            listener.commandCompleted(sj.toString(), result);
        } catch (Exception exp) {
            exp.printStackTrace();
            listener.commandFailed(exp);
        }
    }

    public void write(String text) throws IOException {
        if (process != null && process.isAlive()) {
            process.getOutputStream().write(text.getBytes("UTF-8"));
            process.getOutputStream().flush();
        }
    }
}

class StreamReader extends Thread {

    private InputStream is;
    private CommandListener listener;

    public StreamReader(CommandListener listener, InputStream is) {
        this.is = is;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            int value = -1;
            BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String s;
            while((s = in.readLine())!= null)
            {
                listener.commandOutput(s+"\n");
            }
            //listener.commandOutput(new String(is.readAllBytes(),StandardCharsets.UTF_8));

            //while ((value = in.read()) != -1) {
            //    listener.commandOutput(Character.toString((char) value));
            //}
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}

class ProtectedDocumentFilter extends DocumentFilter {

    private UserInput userInput;

    public ProtectedDocumentFilter(UserInput userInput) {
        this.userInput = userInput;
    }

    public UserInput getUserInput() {
        return userInput;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (offset >= getUserInput().getUserInputStart()) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        if (offset >= getUserInput().getUserInputStart()) {
            super.remove(fb, offset, length); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
