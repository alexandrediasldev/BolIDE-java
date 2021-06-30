package fr.epita.assistants.gui;

import com.formdev.flatlaf.FlatLightLaf;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;

public class mainIde {
    @SneakyThrows
    public static void main(String[] args) {

        FlatLightLaf.install();
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");



        //IDEConfig.INSTANCE.getFrame();
        Intro intro = new Intro();
        intro.setVisible(true);



        intro.pack();
/*
        JFrame frame = new JFrame();

        var setting = new Settings(frame);
        frame.add(setting);
        frame.pack();
        frame.setVisible(true);*/
    }
}
