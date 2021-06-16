package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JPanel {

    public TextEditor() {
        //setSize(1000,1000);
        JTabbedPane Tabs = new JTabbedPane();
        JTextArea text = new JTextArea();
        Font font = new Font("Comic Sans MS", Font.PLAIN, 24);

        text.setFont(font);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);

        JScrollPane scrollpane = new JScrollPane(text);
        scrollpane.setPreferredSize(new Dimension(1300, 700));


        JPanel pan = new JPanel();
        //pan.setSize(1000,600);
        pan.add(scrollpane);
        Tabs.add(pan, "MAIN");
        //Tabs.setPreferredSize(new Dimension(1000,600));
        add(Tabs);
    }
}
