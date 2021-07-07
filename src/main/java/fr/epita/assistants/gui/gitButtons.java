package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Mandatory;

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
                var args = argDialog.getArg();
                if(args != null)
                    if(args != null)
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, ".");
                    else
                        IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, args);
            }
        });

        push.addActionListener(new ActionListener() {

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
        commit.addActionListener(new ActionListener() {

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
        pull.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Pull");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.PULL, args);
            }
        });

        add(add);
        add(push);
        add(pull);
        add(commit);
    }
}
