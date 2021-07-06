package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.editor.EditorPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
        JPanel pannel = new JPanel();
        JButton exit_button = new JButton("x");
        pannel.add(new JLabel(name));
        pannel.add(exit_button);
        exit_button.addActionListener(this);
        tabpane.addTab(name, textEditor);
        tabpane.setTabComponentAt(tabpane.getIndex(), pannel);

        tabpane.setSelectedIndex(tabpane.getIndex());
    }

    public EditorPane getTabpane() {
        return tabpane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IDEConfig.INSTANCE.removeNode(name);
        var index = tabpane.indexOfTab(name);
        tabpane.removeTabAt(index);
        tabpane.removeTab();
    }
}
