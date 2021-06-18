package fr.epita.assistants.gui.optionmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Settings extends JDialog {
    public Settings(Frame parent)
    {
        super(parent,true);
        var pan = new JPanel();
        var layout = new BoxLayout(pan, BoxLayout.Y_AXIS);
        pan.setLayout(layout);

        var pauseOptionPanel =  new Reminder();

        pan.add(pauseOptionPanel);

        var quit = new JButton("Quitter");
        var discardQuit = new JButton("Discard changes");
        var saveQuit = new JButton("Save & exit");

        var pannelQuit = new JPanel();

        pannelQuit.add(discardQuit);
        pannelQuit.add(saveQuit);
        pan.add(pannelQuit);
        add(pan);


        setVisible(true);
    }
}