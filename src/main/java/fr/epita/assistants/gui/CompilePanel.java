package fr.epita.assistants.gui;

import fr.epita.assistants.gui.optionmenu.FontSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CompilePanel extends JMenuBar {

    public CompilePanel(){
        JMenu menu = new JMenu("File");
        Color royal_blue = new Color(0, 35 , 102);
        setBackground(royal_blue);
        JMenuItem item = new JMenuItem("open");
        JMenuItem item1 = new JMenuItem("git");
        menu.add(item);
        menu.add(item1);
        add(menu);

        JMenu menu2 = new JMenu("Settings");
        JMenuItem font = new JMenuItem(new AbstractAction("My Menu Item") {
           FontSettings f = new FontSettings();
            public void actionPerformed(ActionEvent e) {
                    f.setVisible(true);
            }
        });
        JMenuItem color = new JMenuItem("color");
        JMenuItem theme = new JMenuItem("theme");
        menu2.add(color);
        menu2.add(theme);
        menu2.add(font);
        JPanel pan = new JPanel();
        pan.setBackground(royal_blue);
        JPanel pan2 = new JPanel();
        pan2.setBackground(royal_blue);




        JButton button = new JButton("Compile");
        button.setBackground(royal_blue);
        //JButton fermer = new JButton("X");
       // fermer.setBackground(royal_blue);
        add(menu2);
        add(pan);
        //add(new JPanel()); //moving compile button a bit to the right
        //add(new JPanel());
        add(pan2);
        add(button);
        //add(fermer);
    }

}
