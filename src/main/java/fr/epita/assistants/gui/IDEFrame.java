package fr.epita.assistants.gui;

import com.formdev.flatlaf.FlatLightLaf;
import fr.epita.assistants.gui.optionmenu.Settings;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.service.ProjectServiceImplementation;
import lombok.SneakyThrows;
import org.assertj.core.util.Files;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class IDEFrame extends JFrame {

    private TextEditor txt;
    private ProjectServiceImplementation p;
    private Project currentProject;
    @SneakyThrows
    public IDEFrame() // add options to the constructor
    {

        super("BolIDE");
        FlatLightLaf.install();
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");

        var layout = new BorderLayout();
        //layout.setHgap(2);
        //layout.setVgap(2);
        setLayout(layout);

        IDEShell shell = new IDEShell();
        shell.setLayout(new GridLayout());
        add(shell, BorderLayout.SOUTH);

        txt = new TextEditor();
        txt.setLayout(new GridLayout());
        add(txt, BorderLayout.CENTER);

        CompilePanel Bar = new CompilePanel();
        setJMenuBar(Bar);

        p = new ProjectServiceImplementation();
        currentProject = p.load(Files.currentFolder().toPath());
        var panel =new TreePanel(currentProject.getRootNode());
        add(panel , BorderLayout.WEST);

        //setSize(512,512 );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        var setting = new Settings(this);
        setting.show();
        setting.setMaximumSize(new Dimension(300,300));


        pack();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
        setVisible(true);
        var reminder = new Reminder("Remember to take a (15 minutes state mandated) break for your happiness"
                , 120);
        reminder.scheduler();

        gitButtons gitbutton = new gitButtons();
        add(gitbutton, BorderLayout.EAST);
    }

    public TextEditor getTxt() {
        return txt;
    }

    public ProjectServiceImplementation getP() {
        return p;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    private void actionPerformed()
    {
        // add action to perform on click
    }

    /*
    ...
    Add other listeners for events
    call objects using these functions with "this"
     */
}
