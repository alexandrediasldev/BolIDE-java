package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.ArgumentDialog;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.feature.maven.StreamHandler;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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
                Feature.ExecutionReport report;
                var args = argDialog.getArg();
                if(args != null) {
                    if (args != null)
                        report = IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, ".");
                    else
                        report = IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.ADD, args);
                    System.out.println("Add:"+report.isSuccess());
                }
            }
        });

        send.addActionListener(new ActionListener() {

            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Push");
                Feature.ExecutionReport report;
                var args = argDialog.getArg();
                if(args != null) {
                    var pa = "git push " +args;
                    if (System.getProperty("os.name").startsWith("Windows"))
                        pa = "powershell " + pa;
                    var commands = pa.split(" ");
                    ProcessBuilder pb = new ProcessBuilder(commands);
                    pb.directory(new File(String.valueOf(IDEConfig.INSTANCE.getFrame().getCurrentProject().getRootNode().getPath())));
                    Process p = pb.start();
                    new StreamHandler(p.getInputStream()).run(false);

                    new StreamHandler(p.getErrorStream()).run(true);
                    p.waitFor();
                }
            }
        });
        pledge.addActionListener(new ActionListener() {

            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Commit");
                var args = argDialog.getArg();
                Feature.ExecutionReport report;
                if(args != null) {
                    report = IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Git.COMMIT, args);
                    System.out.println("Commit:"+report.isSuccess());
                }


            }
        });
        attract.addActionListener(new ActionListener() {

            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Pull");
                var args = argDialog.getArg();
                Feature.ExecutionReport report;
                if(args != null) {
                        var pa = "git pull " +args;
                        if (System.getProperty("os.name").startsWith("Windows"))
                            pa = "powershell " + pa;
                        ProcessBuilder pb = new ProcessBuilder(pa.split(" "));
                        pb.directory(new File(String.valueOf(IDEConfig.INSTANCE.getFrame().getCurrentProject().getRootNode().getPath())));
                        Process p = pb.start();
                        new StreamHandler(p.getInputStream()).run(false);

                        new StreamHandler(p.getErrorStream()).run(true);
                        p.waitFor();
                }
            }
        });



    }
}
