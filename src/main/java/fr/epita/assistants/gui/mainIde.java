package fr.epita.assistants.gui;

import fr.epita.assistants.gui.optionmenu.Settings;

import javax.swing.*;

public class mainIde {
    public static void main(String[] args) {
        IDEConfig.INSTANCE.getFrame();

        IDEConfig.INSTANCE.switchTheme();
/*
        JFrame frame = new JFrame();

        var setting = new Settings(frame);
        frame.add(setting);
        frame.pack();
        frame.setVisible(true);*/
    }
}
