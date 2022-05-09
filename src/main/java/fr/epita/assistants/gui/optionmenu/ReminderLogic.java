package fr.epita.assistants.gui.optionmenu;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class ReminderLogic {
    private final String message;
    private long ms = 10*1000*60;
    private Timer timer;


    public void setMs(long ms) {
        this.ms = ms * 1000* 60;
        timer.cancel();
        timer.purge();
        scheduler();
    }

    public ReminderLogic(String message, long minutes) {
        this.ms = minutes * 1000 * 60;
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
