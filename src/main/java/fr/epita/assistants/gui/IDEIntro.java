package fr.epita.assistants.gui;

import fr.epita.assistants.gui.utils.JImage;
import fr.epita.assistants.gui.utils.JProjectChooser;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IDEIntro extends JFrame {
    @SneakyThrows
    public IDEIntro()
    {
        JFrame f = this;


        JPanel mainPanel = new JPanel();
        BorderLayout box = new BorderLayout();
        mainPanel.setLayout(box);
        JImage yaka = new JImage("yaka.png");

        JButton loadProject  = new JButton("Load project");
        loadProject.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JProjectChooser projectChooser = new JProjectChooser(f);
                if(projectChooser.choose()) {

                    setVisible(false);
                    IDEConfig.INSTANCE.switchTheme();

                }

            }
        });
        mainPanel.add(loadProject, BorderLayout.SOUTH);


        mainPanel.add(yaka, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        centreWindow(this);


    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3);

        frame.setLocation(x, 0);
    }
}
