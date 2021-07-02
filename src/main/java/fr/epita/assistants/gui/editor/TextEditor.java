package fr.epita.assistants.gui.editor;

import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TextEditor extends JPanel {

    private final RSyntaxTextArea text;
    private String font;
    private int textSize;
    private boolean isdarkmode = true;
    private Theme theme;

    @SneakyThrows
    public TextEditor() {
        JTabbedPane Tabs = new JTabbedPane();

        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        text.setCodeFoldingEnabled(true);

        this.font = "Comic Sans MS";
        this.textSize = 24;

        theme = Theme.load(getClass().getResourceAsStream(
                "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));

        theme.apply(text);
        setFont(font);
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
        text.setFont(new Font(font, Font.PLAIN, textSize));
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        text.setFont(new Font(font, Font.PLAIN, textSize));
    }

    public RSyntaxTextArea getText() {
        return text;
    }

    public void switchTheme() {
        if (isdarkmode) {
            try {
                theme = Theme.load(getClass().getResourceAsStream(
                        "/org/fife/ui/rsyntaxtextarea/themes/idea.xml"));
            } catch (IOException e) {
                System.err.println("Could not load the xml file");
            }
            theme.apply(text);
            isdarkmode = false;
        } else {
            try {
                theme = Theme.load(getClass().getResourceAsStream(
                        "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            } catch (IOException e) {
                System.err.println("Could not load syntax theme");
            }
            theme.apply(text);
            isdarkmode = true;
        }
        setTextSize(textSize);
    }
}
