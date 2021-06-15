package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JPanel {

    public TextEditor() {
        setSize(1000,1000);
        JTabbedPane Tabs = new JTabbedPane();
        JTextArea text = new JTextArea();
        Font font = new Font("MS Gothic", Font.PLAIN, 24);

        text.setFont(font);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);



        text.setPreferredSize(new Dimension(1000, 1000));
        JPanel pan = new JPanel();
        pan.setSize(1000,1000);
        pan.add(text);
        Tabs.add(pan, "MAIN");
        Tabs.setSize(1000,1000);
        add(Tabs);

    }
}
