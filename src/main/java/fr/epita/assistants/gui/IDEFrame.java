package fr.epita.assistants.gui;

import com.formdev.flatlaf.FlatLightLaf;
import fr.epita.assistants.gui.optionmenu.Settings;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.service.ProjectServiceImplementation;
import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class IDEFrame extends JFrame {

    static public RSyntaxTextArea text;

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

        TextEditor txt = new TextEditor();
        txt.setLayout(new GridLayout());
        text = txt.text;
        add(txt, BorderLayout.CENTER);

        CompilePanel Bar = new CompilePanel();
        setJMenuBar(Bar);

        ProjectServiceImplementation p = new ProjectServiceImplementation();
        var project = p.load(Path.of("./"));
        var panel =new TreePanel(project.getRootNode());
        add(panel , BorderLayout.WEST);

        //setSize(512,512 );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        var setting = new Settings(this);
        setting.show();
        setting.setMaximumSize(new Dimension(300,300));


        pack();
        setIconImage(Toolkit.getDefaultToolkit().getImage("./resources/logo.png"));
        setVisible(true);
        var reminder = new Reminder("Remember to take a (15 minutes state mandated) break for your happiness"
                , 120);
        reminder.scheduler();
    }

    static public RSyntaxTextArea getText()
    {
        return text;
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
