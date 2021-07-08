package fr.epita.assistants.gui;

import fr.epita.assistants.gui.editor.EditorPane;
import fr.epita.assistants.gui.editor.TextEditor;
import fr.epita.assistants.gui.optionmenu.ReminderLogic;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.util.ArrayList;

public enum IDEConfig {
    INSTANCE;

    private boolean darkmode = false;
    private String font = "Comic Sans MS";
    private int textSize = 24;
    private IDEFrame frame;
    private final ArrayList<Node> nodes = new ArrayList<>();
    private EditorPane editorPane;
    private ReminderLogic reminder;


    public void setMs(long ms) {
        this.reminder.setMs(ms);
    }
    public void setReminder(ReminderLogic reminder)
    {
        this.reminder = reminder;
    }




    public EditorPane getEditorPane() {
        return editorPane;
    }

    public RSyntaxTextArea getCurrentText(){
        return editorPane.getCurrentText();
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

    public void setFrame(String path)
    {
        frame = new IDEFrame(path);
        editorPane = frame.getEditorPane();
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

        for(var editor : editorPane.getTextEditors())
            editor.switchTheme();
        frame.getShell().switchTheme();
        frame.pack();
    }


    public void removeNode(String name) {
        Node node = null;
        for(var n : nodes)
        {
            if(String.valueOf(n.getPath().getFileName()) == name);
            {
                System.out.println(name);
                node = n;
                break;
            }
        }
        if(node != null)
            nodes.remove(node);
    }

}
