package fr.epita.assistants.gui.toolbar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class CompileButton extends JButton implements ActionListener {
    public CompileButton()
    {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setBorder(emptyBorder);
        setIcon(createIcon("src/main/resources/cog.png", 30, 30));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
