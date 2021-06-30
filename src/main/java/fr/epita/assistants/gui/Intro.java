package fr.epita.assistants.gui;

import com.formdev.flatlaf.FlatLightLaf;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Intro extends JFrame {
    @SneakyThrows
    public Intro()
    {


        JPanel mainPanel = new JPanel();
        BorderLayout box = new BorderLayout();
        mainPanel.setLayout(box);
        JImage yaka = new JImage("yaka.png");

        JButton loadProject  = new JButton("Load project");
        loadProject.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(mainPanel);
                if(option == JFileChooser.APPROVE_OPTION){

                    File file = fileChooser.getSelectedFile();
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                    IDEConfig.INSTANCE.createFrame(file.getPath());
                    IDEConfig.INSTANCE.getFrame();
                    setVisible(false);
                }
            }
        });
        mainPanel.add(loadProject, BorderLayout.SOUTH);


        mainPanel.add(yaka, BorderLayout.CENTER);

        add(mainPanel);
        centreWindow(this);


    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3);

        frame.setLocation(x, 0);
    }
}
