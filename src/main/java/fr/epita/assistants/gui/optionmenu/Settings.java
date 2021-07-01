package fr.epita.assistants.gui.optionmenu;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.Reminder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    public Settings()
    {

        var pan = new JPanel();
        var layout = new BoxLayout(pan, BoxLayout.Y_AXIS);
        pan.setLayout(layout);

        var pauseOptionPanel =  new ReminderSettings();

        pan.add(pauseOptionPanel);

        var quit = new JButton("Quitter");
        /*var discardQuit = new JButton("Discard changes");
        discardQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });*/



        var saveQuit = new JButton("Save & exit");

        saveQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.setMs(pauseOptionPanel.getMs());
                setVisible(false);
            }
        });

        var pannelQuit = new JPanel();

        //pannelQuit.add(discardQuit);
        pannelQuit.add(saveQuit);
        pan.add(pannelQuit);
        add(pan);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
        setSize(400,200);
        setVisible(true);
    }
}
