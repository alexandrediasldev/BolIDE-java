package fr.epita.assistants.gui;

import com.formdev.flatlaf.FlatLightLaf;
import lombok.SneakyThrows;

import javax.swing.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {


        FlatLightLaf.install();
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
        System.setProperty( "flatlaf.menuBarEmbedded", "false" );


        //IDEConfig.INSTANCE.getFrame();
        IDEIntro intro = new IDEIntro();


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
