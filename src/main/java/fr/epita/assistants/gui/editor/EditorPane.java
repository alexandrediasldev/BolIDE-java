package fr.epita.assistants.gui.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditorPane extends JTabbedPane {

    private int index = 0;
    ArrayList<TextEditor> textEditors = new ArrayList<>();
    public EditorPane() {
        //setLayout(new BorderLayout()); //Here
        index = 0;

    }

    public ArrayList<TextEditor> getTextEditors() {
        return textEditors;
    }

    public int getIndex() {
        return index;
    }

    public void addPane(TextEditor editor)
    {
        textEditors.add(editor);
        EditorTab tab = new EditorTab(this, editor);
        index++;
    }
    public void removeTab()
    {
        textEditors.remove(index-1);
        index--;
    }

}
