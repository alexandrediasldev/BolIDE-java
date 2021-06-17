package fr.epita.assistants.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        text.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(text);

        Tabs.addTab("MAIN", scrollpane);
        add(Tabs);


    }
}
