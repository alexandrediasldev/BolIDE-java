package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorPane extends JTabbedPane implements ActionListener {

    public final JButton exit_button = new JButton("x");
    private int index  = 0;

    public void addPane(TextEditor editor)
    {
        addTab(editor.getName(), editor);
        setTabComponentAt(index, exit_button);
        index++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var component = getSelectedComponent();

        System.out.println("click");
        if (component != null)
            remove(component);
    }
}
