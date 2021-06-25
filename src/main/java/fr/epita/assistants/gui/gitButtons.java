package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gitButtons {
    public gitButtons() {
        JButton add = new JButton("add");
        JButton push = new JButton("push");
        JButton commit = new JButton("commit");
        JButton pull = new JButton("pull");

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.add();
            }
        });

        push.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
            }
        });
        commit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
            }
        });
        pull.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
            }
        });
    }
    }
