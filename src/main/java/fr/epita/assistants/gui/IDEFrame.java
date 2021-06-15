package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;

public class IDEFrame extends JFrame {

    public IDEFrame() // add options to the constructor
    {
        setLayout(new BorderLayout());

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
        add(bluePanel, BorderLayout.WEST);
        add(greenPanel, BorderLayout.NORTH);
        add(redPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
