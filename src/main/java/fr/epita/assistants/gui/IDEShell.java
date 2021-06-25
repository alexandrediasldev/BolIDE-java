package fr.epita.assistants.gui;

import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IDEShell extends JPanel {
    private boolean isdarkmode = true;
    private JTextArea text;

    public IDEShell()
    {
        text = new JTextArea();
        Font font = new Font("ARIAL", Font.PLAIN, 24);
        text.setFont(font);
        text.setPreferredSize(new Dimension(1000,200));
        text.setForeground(Color.lightGray);
        text.setBackground(Color.darkGray);
        add(text);
    }
    public void switchTheme() {
        if (isdarkmode) {
            text.setBackground(Color.lightGray);
            text.setForeground(Color.darkGray);
            isdarkmode = false;
        } else {
            text.setForeground(Color.lightGray);
            text.setBackground(Color.darkGray);
            isdarkmode = true;
        }
    }
}
