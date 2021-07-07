package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import lombok.SneakyThrows;

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
        JButton run = new JButton("run");

        run.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Jar Name and main class");
                var args = argDialog.getArg();
                if(args != null) {
                    var pa = "java -cp " + IDEConfig.INSTANCE.getFrame().getCurrentProject().getRootNode().getPath()+
                            java.io.File.separator +"target"+ java.io.File.separator+args;
                    if(System.getProperty("os.name").startsWith("Windows"))
                        pa = "powershell " + pa;
                    System.out.println(pa);
                    ProcessBuilder pb = new ProcessBuilder(pa.split(" "));
                    Process p = pb.start();
                    p.waitFor();


                }
            }
        });
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

        pkg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Package");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.PACKAGE,args);
            }
        });

        test.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Test");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TEST,args);
            }
        });

        install.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Install");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.INSTALL,args);
            }
        });

        exec.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Exec");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.EXEC,args);
            }
        });

        tree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArgumentDialog argDialog = new ArgumentDialog( "Tree");
                var args = argDialog.getArg();
                if(args != null)
                    IDEConfig.INSTANCE.getFrame().getP().execute(IDEConfig.INSTANCE.getFrame().getCurrentProject(), Mandatory.Features.Maven.TREE,args);
            }
        });

        add(compile);
        add(clean);
        add(install);
        add(pkg);
        add(tree);
        add(exec);
        add(test);
        add(run);
    }
}
