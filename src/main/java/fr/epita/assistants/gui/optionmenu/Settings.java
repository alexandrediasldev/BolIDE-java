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
        var b1 = new Button("a");
        var b2 = new Button("b");
        var b3 = new Button("c");
        pan.add(b1);
        pan.add(b2);
        pan.add(b3);
        add(pan);


        setVisible(true);
    }
}
