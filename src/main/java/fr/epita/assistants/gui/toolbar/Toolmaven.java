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

public class Toolmaven extends JMenu {

    public Toolmaven() {

        setIcon(createIcon("macadamia_nut.png", 30, 30));

        var clean = new JMenuItem("clean");
        clean.setIcon(createIcon("clean.png", 20, 20));
        add(clean);

        var compile = new JMenuItem("compile");
        compile.setIcon(createIcon("cog.png", 20, 20));
        add(compile);



        var experiment = new JMenuItem("experiment");
        experiment.setIcon(createIcon("experiment.png", 20, 20));
        add(experiment);

        var wrap = new JMenuItem("wrap");
        wrap.setIcon(createIcon("wrap.png", 20, 20));
        add(wrap);

        var introduce = new JMenuItem("introduce");
        introduce.setIcon(createIcon("hand.png", 20, 20));
        add(introduce);

        var achieve = new JMenuItem("achieve");
        achieve.setIcon(createIcon("medal2.png", 20, 20));
        add(achieve);

        var conifer = new JMenuItem("conifer");
        conifer.setIcon(createIcon("conifer.png", 20, 20));
        add(conifer);

        compile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Compile");
                var args = argDialog.getArg();
                if(args != null) {
                    var proj = IDEConfig.INSTANCE.getFrame().getCurrentProject();
                    IDEConfig.INSTANCE.getFrame().getP().execute(proj, Mandatory.Features.Maven.COMPILE, args);
                }
            }
        });

        clean.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Clean");
                var args = argDialog.getArg();
                if(args != null) {

                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.CLEAN, args);
                }
            }
        });

        wrap.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Package");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.PACKAGE,args);
            }
        });

        experiment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Test");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TEST,args);
            }
        });

        introduce.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Install");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.INSTALL,args);
            }
        });

        achieve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Exec");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.EXEC,args);
            }
        });

        conifer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Tree");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TREE,args);
            }
        });

    }
}