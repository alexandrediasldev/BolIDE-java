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
        var panel = new JPanel();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontsAvailable = ge.getAvailableFontFamilyNames();
        JComboBox<String> fontBox = new JComboBox<String>(fontsAvailable);
        AutoCompletion.enable(fontBox);

        fontBox.setEditable(true);
        panel.add(fontBox);
        var size = new JSpinner(new SpinnerNumberModel(24, 2, 80, 2));
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
        size.addChangeListener(new ChangeListener() {
                                   public void stateChanged(ChangeEvent e) {
                                       JSpinner s = (JSpinner) e.getSource();
                                       IDEConfig.INSTANCE.setTextSize((int) s.getValue());
                                   }
                               });

        panel.add(size);
        panel.add(selectButton);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));

        add(panel);
        pack();
    }
}
