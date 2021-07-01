package fr.epita.assistants.gui.optionmenu;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.Reminder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReminderSettings extends JPanel{
     Integer ms = null;
    public ReminderSettings() {
        var grid = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(grid);
        var slider = new JSlider(JSlider.HORIZONTAL, 0,240,15);
        var labelSlider = new JLabel("Pause Interval");
        var checkBoxPause =  new JCheckBox("Pause Reminder");

        checkBoxPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxPause.isSelected()) {
                    ms = 9999999;
                }
                else {
                    ms = slider.getValue();
                }
            }
        });


        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(!checkBoxPause.isSelected()) {
                    ms = ((JSlider) e.getSource()).getValue();
                }
            }
        });

        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(60);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        add(labelSlider);
        add(slider);
        add(checkBoxPause);
    }
    public int getMs()
    {
        return ms;
    }
}
