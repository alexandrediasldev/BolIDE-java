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

        add(menu2);
    }

}
