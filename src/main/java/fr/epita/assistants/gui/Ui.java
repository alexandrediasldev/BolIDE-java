package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;

public class Ui {
    private static class exit_listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Title");

        GridLayout gridText = new GridLayout(2,1);
        GridLayout buttomLayout = new GridLayout();

        JTextField field = new JTextField();

        JButton button = new JButton("Exit Here");
        button.addActionListener(new exit_listener());

        frame.setLayout(gridText);
        frame.add(field);
        frame.add(button);

        frame.pack(); //auto size
        frame.setVisible(true);
    }
}
