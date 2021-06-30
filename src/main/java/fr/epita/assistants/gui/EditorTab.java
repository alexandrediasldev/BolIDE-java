package fr.epita.assistants.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorTab implements ActionListener {
    private final EditorPane tabpane;
    private final String name;

    public EditorTab(EditorPane tabpane, String name) {
        this.tabpane = tabpane;
        this.name = name;
    }

    public EditorPane getTabpane() {
        return tabpane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        var index = tabpane.indexOfTab(name);
        tabpane.removeTabAt(index);
    }
}
