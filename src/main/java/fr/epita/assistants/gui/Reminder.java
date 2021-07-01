package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Timer;

public class Reminder {
    private final String message;
    private long ms;
    private Timer timer;


    public void setMs(long ms) {
        this.ms = ms * 1000;
        //System.out.println(this.ms);
        timer.cancel();
        timer.purge();
        scheduler();
    }

    public Reminder(String message, long minutes) {
        this.ms = minutes * 1000;
        this.message = message;
        timer = new Timer();
    }

    public void scheduler()
    {
        timer = new Timer();
                timer.schedule(
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
