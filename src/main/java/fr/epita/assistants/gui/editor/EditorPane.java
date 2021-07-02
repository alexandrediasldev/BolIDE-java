package fr.epita.assistants.gui.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorPane extends JTabbedPane {

    private int index  = 0;

    public int getIndex() {
        return index;
    }

    public void addPane(TextEditor editor)
    {
        EditorTab tab = new EditorTab(this, editor);
        index++;
    }

}
