package fr.epita.assistants.gui.shell;



import com.google.common.base.Ascii;
import com.jediterm.pty.PtyProcessTtyConnector;
import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.emulator.ColorPalette;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalWidget;
import com.jediterm.terminal.ui.TerminalWidgetListener;
import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import com.pty4j.PtyProcess;
import com.pty4j.PtyProcessBuilder;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.IDEFrame;
import fr.epita.assistants.myide.domain.entity.Project;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BasicTerm extends JPanel{

    Project project;
    public BasicTerm(Project project)
    {
        setLayout(new GridLayout());
          this.project = project;
          var term = createTerminalWidget();
          //term.getTerminal().setCurrentPath(String.valueOf(project.getRootNode().getPath()));
          add(term);

    }
    public JediTermWidget createTerminalWidget() {

        SettingsProvider settings;
        var defSettings = new DefaultSettingsProvider();

        settings = new DefaultSettingsProvider()
        {
            @Override
            public ColorPalette getTerminalColorPalette() {
                ColorPalette palette = new ColorPalette() {


                    @Override
                    protected Color getForegroundByColorIndex(int i) {
                        if(UIUtil.isWindows)
                            return UIManager.getDefaults().getColor("TextPane.foreground");
                        return defSettings.getTerminalColorPalette().getForeground(TerminalColor.WHITE);
                    }

                    @Override
                    protected Color getBackgroundByColorIndex(int i) {

                            return UIManager.getDefaults().getColor("TextPane.background");

                    }
                };
                return palette;
            }
        };


        JediTermWidget widget = new JediTermWidget(80, 10, settings);
        widget.setTtyConnector(createTtyConnector());
        widget.start();

        return widget;
    }

    private TtyConnector createTtyConnector() {
        try {
            Map<String, String> envs = System.getenv();
            String[] command;
            if (UIUtil.isWindows) {
                command = new String[]{"cmd.exe", "/K", "cd", String.valueOf(project.getRootNode().getPath())};
                //command = new String[]{"cmd.exe"};
            } else {
                command = new String[]{"/bin/bash", "--login"};
                envs = new HashMap<>(System.getenv());
                envs.put("PWD",String.valueOf(project.getRootNode().getPath()));
                envs.put("TERM", "xterm-256color");
            }

            PtyProcess process = new PtyProcessBuilder().setCommand(command).setEnvironment(envs).start();
            //OutputStream is = process.getOutputStream();

            //String comman = new String("cd "+ project.getRootNode().getPath()+"\r\n");
            //is.write(comman.getBytes(StandardCharsets.UTF_8));



            var connector = new PtyProcessTtyConnector(process, StandardCharsets.UTF_8);

            return connector;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static void main(String[] args) {
        // Create and show this application's GUI in the event-dispatching thread.

    }
}
