package fr.epita.assistants.gui.optionmenu;

import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {
    public Settings()
    {

        var pan = new JPanel();
        var layout = new BoxLayout(pan, BoxLayout.Y_AXIS);
        pan.setLayout(layout);

        var pauseOptionPanel =  new ReminderSettings();

        pan.add(pauseOptionPanel);

        var quit = new JButton("Quitter");
        var discardQuit = new JButton("Discard changes");
        var saveQuit = new JButton("Save & exit");

        var pannelQuit = new JPanel();

        pannelQuit.add(discardQuit);
        pannelQuit.add(saveQuit);
        pan.add(pannelQuit);
        add(pan);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
        setSize(400,200);
        setVisible(true);
    }
}
