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

    @SneakyThrows
    public TextEditor() {
        JTabbedPane Tabs = new JTabbedPane();

        RSyntaxTextArea text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        text.setCodeFoldingEnabled(true);
        Theme theme = Theme.load(getClass().getResourceAsStream( "light.xml" ));
        Font font = new Font("Comic Sans MS", Font.PLAIN, 24);

        text.setFont(font);
        text.setForeground(Color.white);
        text.setBackground(Color.darkGray);
        text.setColumns(80);
        text.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(text);

        Tabs.addTab("MAIN", scrollpane);
        add(Tabs);


    }
}
