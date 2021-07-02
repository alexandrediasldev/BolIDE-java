package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.editor.EditorPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorTab implements ActionListener{
    private EditorPane tabpane;
    private String name;

    public EditorTab(EditorPane tabpane, String name) {
        this.tabpane = tabpane;
        this.name = name;
    }

    public EditorTab(EditorPane tabpane, TextEditor textEditor)
    {


        this.tabpane = tabpane;
        this.name = textEditor.getName();

        JButton exit_button = new JButton("x");
        exit_button.addActionListener(this);
        tabpane.addTab(name, textEditor);
        tabpane.setTabComponentAt(tabpane.getIndex(), exit_button);
    }

    public EditorPane getTabpane() {
        return tabpane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        var index = tabpane.indexOfTab(name);
        tabpane.removeTabAt(index);
        tabpane.removeTab();
    }
}
