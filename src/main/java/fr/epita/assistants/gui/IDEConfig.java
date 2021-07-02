package fr.epita.assistants.gui;

import java.awt.*;

import fr.epita.assistants.gui.editor.EditorPane;
import fr.epita.assistants.gui.editor.TextEditor;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import lombok.SneakyThrows;
import org.w3c.dom.Text;

import javax.swing.*;
import java.util.ArrayList;

public enum IDEConfig {
    INSTANCE;

    private boolean darkmode = true;
    private String font = "Comic Sans MS";
    private int textSize = 24;
    private final IDEFrame frame = new IDEFrame();
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final EditorPane editorPane = frame.getEditorPane();

    public EditorPane getEditorPane() {
        return editorPane;
    }
    public TextEditor getTextEditor(String name)
    {
        var editors = editorPane.getTextEditors();
        for(var editor : editors)
        {
            if(editor.getName().equals(name))
                return editor;
        }
        throw new IllegalArgumentException("Name of text editors not found");
    }
    public boolean getDarkMode()
    {
        return darkmode;
    }
    public IDEFrame getFrame() {
        return frame;
    }
    public void add()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.ADD, ".");
    }

    public void push()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.PUSH, "origin", "master");
    }

    public void pull()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.PULL);
    }

    public void commit(String msg)
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.COMMIT, msg);
    }

    public void setFont(String font)
    {

        this.font = font;
        for(var editor : editorPane.getTextEditors())
        {
            editor.setFont(font);
        }

    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
        for(var editor : editorPane.getTextEditors())
        {
            editor.setTextSize(textSize);
        }

    }

    public String getFont() {
        return font;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setContent(String content, String fileName)
    {
        int index = editorPane.indexOfTab(fileName);
        if(index >= 0)
        {
            editorPane.setSelectedIndex(index);
            return;
        }
        TextEditor editor = new TextEditor();
        editor.setName(fileName);
        editorPane.addPane(editor);

        editor.getText().setText(content);

    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @SneakyThrows
    public void switchTheme()
    {
        if (darkmode) {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            darkmode = false;
        }
        else
        {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
            darkmode = true;
        }
        SwingUtilities.updateComponentTreeUI(frame);

        frame.getShell().switchTheme();
        frame.pack();
    }
}
