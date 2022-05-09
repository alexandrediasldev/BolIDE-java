package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.optionmenu.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPopup extends JFrame implements ActionListener{

    private RSyntaxTextArea textArea;
    private JTextField searchField;

    public SearchPopup() {
        super("Search");

        textArea = IDEConfig.INSTANCE.getCurrentText();

        var panel = new JPanel();
        // Create a toolbar with searching options.
        JToolBar toolBar = new JToolBar();
        searchField = new JTextField(30);
        searchField.setName("Find");
        toolBar.add(searchField);
        JButton prevButton = new JButton("Find Previous");
        prevButton.setActionCommand("FindPrev");
        prevButton.addActionListener(this);
        toolBar.add(prevButton);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prevButton.doClick(0);
            }
        });

        final JButton nextButton = new JButton("Find Next");
        nextButton.setActionCommand("FindNext");
        nextButton.addActionListener(this);
        toolBar.add(nextButton);
        panel.add(toolBar);
        add(panel);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
        pack();

    }

    public void actionPerformed(ActionEvent e) {

        // "FindNext" => search forward, "FindPrev" => search backward
        if (textArea == null)
            return;

        String command = e.getActionCommand();
        boolean forward = "FindNext".equals(command);

        // Create an object defining our search parameters.
        SearchContext context = new SearchContext();
        String text = searchField.getText();
        if (text.length() == 0) {
            return;
        }
        context.setSearchFor(text);
        context.setRegularExpression(true);
        context.setSearchForward(forward);
        context.setWholeWord(false);

        boolean found = SearchEngine.find(textArea, context).wasFound();
    }

    public void setTextArea(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }

}
