package fr.epita.assistants.gui;

import javax.swing.*;

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
                        JOptionPane.showMessageDialog(null, message, "BolIDE: pause", JOptionPane.INFORMATION_MESSAGE);
                        scheduler();
                    }
                },
                ms
        );
    }
}
