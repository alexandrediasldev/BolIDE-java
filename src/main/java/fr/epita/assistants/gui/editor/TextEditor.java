package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class TextEditor extends JPanel {

    private final RSyntaxTextArea text;
    private String font;
    private int textSize;

    private Theme theme;

    @SneakyThrows
    public TextEditor() {

        setLayout(new GridLayout());
        text = new RSyntaxTextArea();
        text.setCodeFoldingEnabled(true);

        this.font = IDEConfig.INSTANCE.getFont();
        this.textSize = IDEConfig.INSTANCE.getTextSize();

        setFont(font);
        text.setColumns(80);
        text.setLineWrap(true);
        RTextScrollPane scrollpane = new RTextScrollPane(text);
        switchTheme();

        add(scrollpane);
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
        if (!IDEConfig.INSTANCE.getDarkMode()) {
            try {
                theme = Theme.load(getClass().getResourceAsStream(
                        "/org/fife/ui/rsyntaxtextarea/themes/idea.xml"));
            } catch (IOException e) {
                System.err.println("Could not load the xml file");
            }
            theme.apply(text);

        } else {
            try {
                theme = Theme.load(getClass().getResourceAsStream(
                        "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            } catch (IOException e) {
                System.err.println("Could not load syntax theme");
            }
            theme.apply(text);

        }
        setTextSize(textSize);
    }
}
