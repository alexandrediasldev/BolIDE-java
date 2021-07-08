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

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class CompileButton extends JButton implements ActionListener {
    public void RedirectFailingErrorSad()
    {

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

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);


                ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                PrintStream ps2 = new PrintStream(baos);
                PrintStream old2 = System.err;
                System.setOut(ps2);



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

                    System.out.flush();
                    System.setOut(old);
                    String out = baos.toString();

                    System.err.flush();
                    System.setOut(old2);
                    String err = baos2.toString();

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

                    System.out.flush();
                    System.setOut(old);
                    String out = baos.toString();

                    System.err.flush();
                    System.setOut(old2);
                    String err = baos2.toString();

                    JOptionPane.showMessageDialog(this, out + "\n" + err);
                }
            }
        }

}
