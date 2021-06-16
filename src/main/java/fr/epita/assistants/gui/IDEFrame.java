package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.service.ProjectServiceImplementation;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class IDEFrame extends JFrame {

    public IDEFrame() // add options to the constructor
    {
        var layout = new BorderLayout();
        layout.setHgap(2);
        layout.setVgap(2);
        setLayout(layout);

        Panel blackPanel = new Panel();
        blackPanel.setBackground(Color.BLACK);
        blackPanel.setVisible(true);

        Panel bluePanel = new Panel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setVisible(true);

        Panel greenPanel = new Panel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setVisible(true);

        Panel redPanel = new Panel();
        redPanel.setBackground(Color.RED);
        redPanel.setVisible(true);

        add(blackPanel, BorderLayout.EAST);
//        add(bluePanel, BorderLayout.WEST);
//        add(greenPanel, BorderLayout.NORTH);
        add(redPanel, BorderLayout.SOUTH);

        TextEditor txt = new TextEditor();
        add(txt, BorderLayout.CENTER);
        CompilePanel Bar = new CompilePanel();
        setJMenuBar(Bar);

        ProjectServiceImplementation p = new ProjectServiceImplementation();
        var project = p.load(Path.of("./"));
        var panel =new TreePanel(project.getRootNode());
        add(panel , BorderLayout.WEST);

        setSize(512,512 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
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
