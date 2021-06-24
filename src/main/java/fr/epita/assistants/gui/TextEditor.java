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
    private String font;
    private int textSize;

    @SneakyThrows
    public TextEditor() {
        JTabbedPane Tabs = new JTabbedPane();

        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        text.setCodeFoldingEnabled(true);

        this.font = "Comic Sans MS";
        this.textSize = 24;
        setFont(font);

        Theme theme = Theme.load(getClass().getResourceAsStream(
                "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));

        theme.apply(text);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);
        text.setColumns(80);
        text.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(text);

        Tabs.addTab("MAIN", scrollpane);
        add(Tabs);
    }

    public void setFont(String font) {
        this.font = font;
        text.setFont( new Font(font, Font.PLAIN, textSize));
    }
    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
        text.setFont( new Font(font, Font.PLAIN, textSize));
    }

    public RSyntaxTextArea getText() {
        return text;
    }
}
