package fr.epita.assistants.gui.optionmenu;

import fr.epita.assistants.gui.TextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontSettings extends JFrame {
    public FontSettings(TextEditor textEditor) {
        super("Font settings");
        var pannel = new JPanel();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsAvailable = ge.getAvailableFontFamilyNames();
        JComboBox<String> fontBox = new JComboBox<String>(fontsAvailable);
        AutoCompletion.enable(fontBox);

        fontBox.setEditable(true);
        pannel.add(fontBox);
        var selectButton = new JButton("SELECT");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fontBox.getSelectedIndex() != -1) {
                    var selected  = fontBox.getItemAt
                            (fontBox.getSelectedIndex());
                    var text = textEditor.getText();
                    text.setFont(new Font(selected, Font.PLAIN, 23));
                }
            }
        });
        pannel.add(selectButton);


        add(pannel);
        pack();
    }
}
