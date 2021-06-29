package fr.epita.assistants.gui;


import javax.swing.*;
import java.awt.*;


public class IDEShell extends JPanel {
    public IDEShell()
    {
        JTextArea text = new JTextArea();
        Font font = new Font("ARIAL", Font.PLAIN, 24);
        text.setFont(font);
        text.setPreferredSize(new Dimension(1000,200));
        text.setForeground(Color.lightGray);
        text.setBackground(Color.darkGray);
        add(text);
    }
}
