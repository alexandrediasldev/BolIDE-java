package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Reminder {
    private final String message;
    private final long ms;

    public Reminder(String message, long seconds) {
        this.ms = seconds * 1000;
        this.message = message;
    }

    public void scheduler()
    {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        JOptionPane pane = new JOptionPane(message);
                        Dialog dialog = pane.createDialog("BolIDE");
                        dialog.setModal(false);
                        dialog.setLocation(0, 0);
                        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
                        dialog.show();

                        scheduler();
                    }
                },
                ms
        );
    }
}
