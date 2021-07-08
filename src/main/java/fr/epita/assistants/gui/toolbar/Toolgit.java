package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.ArgumentDialog;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.Mandatory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class Toolgit extends JMenu {

    public Toolgit() {

        setIcon(createIcon("sun.png", 30, 30));
        var amplify = new JMenuItem("amplify");
        amplify.setIcon(createIcon("plus.png", 20, 20));
        add(amplify);

        var pledge = new JMenuItem("pledge");
        pledge.setIcon(createIcon("bluecheck.png", 20, 20));
        add(pledge);

        var send = new JMenuItem("send");
        send.setIcon(createIcon("up_right_arrow.png", 20, 20));
        add(send);

        var attract = new JMenuItem("attract");
        attract.setIcon(createIcon("down_left_arrow.png", 20, 20));
        add(attract);

        amplify.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                ArgumentDialog argDialog = new ArgumentDialog( "Add");
                var args = argDialog.getArg();
                if(args != null)
                    if(args != null)
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, ".");
                    else
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, args);
            }
        });

        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //IDEConfig.INSTANCE.push();
                ArgumentDialog argDialog = new ArgumentDialog( "Push");
                var args = argDialog.getArg();
                if(args != null)
                    if(args != null)
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.PUSH, "origin", "master");
                    else
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.PUSH, args);
            }
        });
        pledge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //String msg = JOptionPane.showInputDialog("Enter a commit message");
                //
                ArgumentDialog argDialog = new ArgumentDialog( "Commit");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.COMMIT, args);

            }
        });
        attract.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Pull");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.PULL, args);
            }
        });



    }
}
