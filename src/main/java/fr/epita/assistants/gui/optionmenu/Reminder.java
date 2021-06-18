package fr.epita.assistants.gui.optionmenu;

import javax.swing.*;
import java.awt.*;

public class Reminder extends JPanel{

    public Reminder() {
        var grid = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(grid);
        var slider = new JSlider(JSlider.HORIZONTAL, 0,240,10);
        var labelSlider = new JLabel("Pause Interval");
        var checkBoxPause =  new JCheckBox("Pause Reminder");

        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(60);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        add(labelSlider);
        add(slider);
        add(checkBoxPause);
    }
}
