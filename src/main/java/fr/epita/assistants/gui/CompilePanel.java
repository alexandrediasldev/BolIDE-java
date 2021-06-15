package fr.epita.assistants.gui;

import javax.swing.*;

public class CompilePanel extends JMenuBar {

    public CompilePanel(){
        JMenu menu = new JMenu("File");
        JMenuItem item = new JMenuItem("open");
        JMenuItem item1 = new JMenuItem("git");
        menu.add(item);
        menu.add(item1);
        add(menu);

        JMenu menu2 = new JMenu("Settings");
        JMenuItem color = new JMenuItem("color");
        JMenuItem theme = new JMenuItem("theme");
        menu2.add(color);
        menu2.add(theme);
        JPanel pan = new JPanel();
        JPanel pan2 = new JPanel();




        JButton button = new JButton("Compile");
        JButton fermer = new JButton("X");
        add(menu2);
        add(pan);
        //add(new JPanel()); //moving compile button a bit to the right
        //add(new JPanel());
        add(button);
        add(pan2);
        add(fermer);
    }

}
