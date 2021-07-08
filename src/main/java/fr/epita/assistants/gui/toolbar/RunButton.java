package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.ArgumentDialog;
import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.feature.maven.StreamHandler;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class RunButton extends JButton implements ActionListener {
    public RunButton()
    {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setMaximumSize(new Dimension(35,35));
        setBorder(emptyBorder);
        setIcon(createIcon("run.png", 30, 30));
        addActionListener(this);
    }
    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        ArgumentDialog argDialog = new ArgumentDialog("Jar Name and main class");
        var args = argDialog.getArg();
        if (args != null) {
            var pa = "java -cp " + IDEConfig.INSTANCE.getFrame().getCurrentProject().getRootNode().getPath() +
                    java.io.File.separator + "target" + java.io.File.separator + args;
            if (System.getProperty("os.name").startsWith("Windows"))
                pa = "powershell " + pa;
            System.out.println(pa);
            ProcessBuilder pb = new ProcessBuilder(pa.split(" "));
            Process p = pb.start();
            new StreamHandler(p.getInputStream()).run(false);

            new StreamHandler(p.getErrorStream()).run(true);
            p.waitFor();
        }
    }
}
