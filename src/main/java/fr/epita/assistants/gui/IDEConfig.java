package fr.epita.assistants.gui;

import java.awt.*;

import fr.epita.assistants.gui.editor.EditorPane;
import fr.epita.assistants.gui.editor.TextEditor;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import lombok.SneakyThrows;

import javax.swing.*;
import java.util.ArrayList;

public enum IDEConfig {
    INSTANCE;

    private boolean darkmode = true;
    private final IDEFrame frame = new IDEFrame();
    private final ArrayList<Node> nodes = new ArrayList<>();
    private final EditorPane editorPane = frame.getEditorPane();

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
       // frame.getTxt().setFont(font);

    }

    public void setTextSize(int textSize)
    {
        //frame.getTxt().setTextSize(textSize);
    }

    public void setContent(String content, String fileName)
    {

        TextEditor editor = new TextEditor();
        editor.setName(fileName);
        editorPane.addPane(editor);

        editor.getText().setText(content);
       // frame.getTxt().getText().setText(content);
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
//        frame.getEditorPane().switchTheme();
        frame.getShell().switchTheme();
        frame.pack();
    }
}
