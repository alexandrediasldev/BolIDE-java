package fr.epita.assistants.gui.toolbar;

import javax.swing.*;
import java.awt.*;

public class MiddleButtons extends JPanel {
    public MiddleButtons()
    {
        Color royal_blue = new Color(65, 105 , 225);

        var saveButton = new SaveButton();
        saveButton.setBackground(royal_blue);


        var compileButton = new CompileButton();
        compileButton.setBackground(royal_blue);

        var runButton = new RunButton();
        runButton.setBackground(royal_blue);
        setLayout(new GridLayout(1,3));
        add(saveButton);
        add(compileButton);
        add(runButton);
    }
}
