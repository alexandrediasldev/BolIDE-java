package fr.epita.assistants.gui;

import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TextEditor extends JPanel {

    private RSyntaxTextArea text;

    @SneakyThrows
    public TextEditor() {
        JTabbedPane Tabs = new JTabbedPane();

        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        text.setCodeFoldingEnabled(true);

        Font font = new Font("Comic Sans MS", Font.PLAIN, 24);
        Theme theme = Theme.load(getClass().getResourceAsStream(
                "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));

        theme.apply(text);
        text.setFont(font);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);
        text.setColumns(80);
        text.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(text);

        Tabs.addTab("MAIN", scrollpane);
        add(Tabs);
    }

    public RSyntaxTextArea getText() {
        return text;
    }

    public RSyntaxTextArea getText() {
        return text;
    }
}
