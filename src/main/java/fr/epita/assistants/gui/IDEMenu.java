package fr.epita.assistants.gui;

import fr.epita.assistants.gui.optionmenu.FontSettings;
import fr.epita.assistants.gui.optionmenu.ReminderSettings;
import fr.epita.assistants.gui.toolbar.CompileButton;
import fr.epita.assistants.gui.toolbar.MiddleButtons;
import fr.epita.assistants.gui.toolbar.RunButton;
import fr.epita.assistants.gui.toolbar.SaveButton;
import fr.epita.assistants.gui.utils.FileOperations;
import fr.epita.assistants.gui.utils.JProjectChooser;
import lombok.SneakyThrows;

import javax.media.Manager;
import javax.media.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IDEMenu extends JMenuBar {

    @SneakyThrows
    public IDEMenu() {
        //setLayout(new GridLayout(1,3));
        JMenu menu = new JMenu("File");
        var reminder = new ReminderSettings();
        Color royal_blue = new Color(65, 105 , 225);


        JMenuItem item = new JMenuItem("open");

        item.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = IDEConfig.INSTANCE.getFrame();
                JProjectChooser projectChooser = new JProjectChooser(mainFrame);

                if(projectChooser.choose())

                    mainFrame.setVisible(false);

            }
        });
        JMenuItem item1 = new JMenuItem("git");



        JMenuItem item2 = new JMenuItem("music");

        final boolean[] music = {false};
        ClassLoader classLoader = getClass().getClassLoader();
        var res = classLoader.getResource("sussy.wav");


        final Player p = Manager.createRealizedPlayer(res);
        item2.addActionListener(new ActionListener() {

            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!music[0])
                {
                    System.out.println("playing music");
                    music[0] = true;
                    p.start();
                }
                else
                {
                    music[0] = false;
                    p.stop();
                }
            }
        });

        menu.add(item);
        menu.add(item1);

        menu.add(item2);



        JMenu menu2 = new JMenu("Settings");
        JMenuItem font = new JMenuItem(new AbstractAction("Font") {
           FontSettings f = new FontSettings();
            public void actionPerformed(ActionEvent e) {
                    f.setVisible(true);
            }
        });
        JMenuItem color = new JMenuItem("color");
        JMenuItem pause = new JMenuItem("pause");

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reminder.setVisible(true);
            }
        });

        JMenuItem theme = new JMenuItem("Switchtheme");
        theme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.switchTheme();
            }
        });

        menu2.add(pause);
        menu2.add(color);
        menu2.add(theme);
        menu2.add(font);
        JPanel pan = new JPanel();
        pan.setBackground(royal_blue);
        JPanel pan2 = new JPanel();
        pan2.setBackground(royal_blue);







        //JButton button = new JButton("Compile");
        //button.setBackground(royal_blue);
        //JButton fermer = new JButton("X");
       // fermer.setBackground(royal_blue);

        JPanel left = new JPanel();
        add(menu);
        add(menu2);
        add(pan);

        add(pan2);
        //add(button);
        MiddleButtons middle = new MiddleButtons();

        add(middle);
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        JPanel pan5 = new JPanel();

        pan3.setBackground(royal_blue);
        pan4.setBackground(royal_blue);
        pan5.setBackground(royal_blue);
        add(pan3); //moving compile button a bit to the right
        add(pan4);
        setBackground(royal_blue);
        //add(left);
        //add(middle);
        //add(fermer);
    }


}
