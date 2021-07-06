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


                ArgumentDialog argDialog = new ArgumentDialog( "Add");
                var s = argDialog.getArg();
                if(s != null)
                    IDEConfig.INSTANCE.add(s);
            }
        });

        push.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //IDEConfig.INSTANCE.push();
                ArgumentDialog argDialog = new ArgumentDialog( "Push");
                var s = argDialog.getArg();
                if(s != null)
                    IDEConfig.INSTANCE.push(s);
            }
        });
        commit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //String msg = JOptionPane.showInputDialog("Enter a commit message");
                //
                ArgumentDialog argDialog = new ArgumentDialog( "Commit");
                var s = argDialog.getArg();
                if(s != null)
                    IDEConfig.INSTANCE.commit(s);

            }
        });
        pull.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Pull");
                var s = argDialog.getArg();
                if(s != null)
                    IDEConfig.INSTANCE.pull(s);
            }
        });

        add(add);
        add(push);
        add(pull);
        add(commit);
    }
}
