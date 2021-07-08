package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.ArgumentDialog;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.Mandatory;


import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.bean.playerbean.MediaPlayer;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class CompileButton extends JButton implements ActionListener {
    PrintStream old;
    PrintStream old2;
    ByteArrayOutputStream baos;
    ByteArrayOutputStream baos2;
    String out = null;
    String err = null;
    PrintStream ps = null;
    PrintStream ps2 = null;

    public void StartRedirectError()
    {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);


        baos2 = new ByteArrayOutputStream();
        ps2 = new PrintStream(baos);
        old2 = System.err;
        System.setOut(ps2);
    }

    public void StopRedirectError()
    {
        System.out.flush();
        System.setOut(old);
        out = baos.toString();

        System.err.flush();
        System.setOut(old2);
        err = baos2.toString();
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
                    JOptionPane.showMessageDialog(this, out + "\n" + err);
                }
            }
        }

}
