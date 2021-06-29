package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Mandatory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mavenButton extends JPanel {
    public mavenButton()
    {
        JButton compile = new JButton("compile");
        JButton clean = new JButton("clean");
        JButton pkg = new JButton("pkg");
        JButton test = new JButton("test");
        JButton install = new JButton("install");
        JButton exec = new JButton("exec");
        JButton tree = new JButton("tree");

        compile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.COMPILE);
            }
        });

        clean.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.CLEAN);
            }
        });

        pkg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.PACKAGE);
            }
        });

        test.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TEST);
            }
        });

        install.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.INSTALL);
            }
        });

        exec.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.EXEC);
            }
        });

        tree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TREE);
            }
        });

        add(compile);
        add(clean);
        add(install);
        add(pkg);
        add(tree);
        add(exec);
        add(test);
    }
}
