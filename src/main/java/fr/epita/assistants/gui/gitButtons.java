package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gitButtons extends JPanel {
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
                IDEConfig.INSTANCE.push();
            }
        });
        commit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = JOptionPane.showInputDialog("Enter a commit message");
                IDEConfig.INSTANCE.commit(msg);
            }
        });
        pull.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.pull();
            }
        });

        add(add);
        add(push);
        add(pull);
        add(commit);
    }
}
