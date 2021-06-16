package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JPanel {

    public TextEditor() {
        JTabbedPane Tabs = new JTabbedPane();
        JTextArea text = new JTextArea();
        Font font = new Font("Comic Sans MS", Font.PLAIN, 24);

        text.setFont(font);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);
        text.setColumns(80);
        JScrollPane scrollpane = new JScrollPane(text);
        scrollpane.setPreferredSize(new Dimension(1570, 650));

        JPanel pan = new JPanel();
        pan.add(scrollpane);
        Tabs.add(pan, "MAIN");
        add(Tabs);
    }
}
