package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.ArgumentDialog;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.Mandatory;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class CompileButton extends JButton implements ActionListener {
    // all this field are here to redirect sout/serr into popup
    ByteArrayOutputStream outStream;
    ByteArrayOutputStream errStream;
    StringBuilder outLog = new StringBuilder();
    StringBuilder errLog = new StringBuilder();
    PrintStream newOut = null;
    PrintStream oldOut = null;
    PrintStream newErr = null;
    PrintStream oldErr = null;

    public void StartRedirectError()
    {
        outStream = new ByteArrayOutputStream();
        newOut = new PrintStream(outStream);
        oldOut = System.out;
        System.setOut(newOut);


        errStream = new ByteArrayOutputStream();
        newErr = new PrintStream(outStream);
        oldErr = System.err;
        System.setOut(newErr);
    }

    public void StopRedirectError()
    {
        outLog.setLength(0);
        errLog.setLength(0);

        System.out.flush();
        System.setOut(oldOut);
        outLog.append(outStream.toString());

        System.err.flush();
        System.setOut(oldErr);
        errLog.append(errStream.toString());
    }

    public CompileButton()
    {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setMaximumSize(new Dimension(35,35));
        setBorder(emptyBorder);
        setIcon(createIcon("cog.png", 30, 30));
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

            ArgumentDialog argDialog = new ArgumentDialog( "Compile");
            var args = argDialog.getArg();
            if(args != null) {
                var proj = IDEConfig.INSTANCE.getFrame().getCurrentProject();


                StartRedirectError();


                var executeReport = IDEConfig.INSTANCE.getFrame().getP().execute(proj, Mandatory.Features.Maven.COMPILE, args);



                if (executeReport.isSuccess())
                {
                    /*var res = getClass().getClassLoader().getResource("yes.wav");

                    Player p = null;

                    try {
                        p = Manager.createRealizedPlayer(res);
                    } catch (IOException | NoPlayerException | CannotRealizeException ioException) {
                        System.err.println("Failed to load file: " + res);
                    }

                    p.start();*/

                    StopRedirectError();

                    var compilationPopup = new CompilationPopup(true, "Compilation success");
                    compilationPopup.setVisible(true);
                }
                else
                {
                    /*var res = getClass().getClassLoader().getResource("bruh.wav");

                    Player p = null;

                    try {
                        p = Manager.createRealizedPlayer(res);
                    } catch (IOException | NoPlayerException | CannotRealizeException ioException) {
                        System.err.println("Failed to load file: " + res);
                    }

                    p.start();*/

                    var compilationPopup = new CompilationPopup(false, "Compilation failed");
                    compilationPopup.setVisible(true);


                    StopRedirectError();
                    JOptionPane.showMessageDialog(this, outLog.toString() + "\n" + errLog.toString());
                }
            }
        }

}
