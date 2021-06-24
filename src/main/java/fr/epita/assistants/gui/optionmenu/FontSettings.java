package fr.epita.assistants.gui.optionmenu;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.TextEditor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontSettings extends JFrame {
    public FontSettings() {
        super("Font settings");
        var pannel = new JPanel();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsAvailable = ge.getAvailableFontFamilyNames();
        JComboBox<String> fontBox = new JComboBox<String>(fontsAvailable);
        AutoCompletion.enable(fontBox);

        fontBox.setEditable(true);
        pannel.add(fontBox);
        var size = new JSpinner();
        var selectButton = new JButton("SELECT");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fontBox.getSelectedIndex() != -1) {
                    var selected = fontBox.getItemAt
                            (fontBox.getSelectedIndex());
                    IDEConfig.INSTANCE.setFont(selected);
                    IDEConfig.INSTANCE.setTextSize((int) size.getValue());
                }
            }

        });

        pannel.add(size);
        pannel.add(selectButton);


        add(pannel);
        pack();
    }
}
