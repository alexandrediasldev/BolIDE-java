package fr.epita.assistants.gui;

import fr.epita.assistants.gui.optionmenu.FontSettings;
import fr.epita.assistants.gui.optionmenu.ReminderSettings;
import fr.epita.assistants.gui.toolbar.MiddleButtons;
import fr.epita.assistants.gui.utils.JProjectChooser;
import lombok.SneakyThrows;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class IDEMenu extends JMenuBar {


    public IDEMenu() {
        JMenu menu = new JMenu("File");
        var reminder = new ReminderSettings();
        Color royal_blue = new Color(65, 105 , 225);

        JMenuItem item = new JMenuItem("Open");

        item.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = IDEConfig.INSTANCE.getFrame();
                JProjectChooser projectChooser = new JProjectChooser(mainFrame);

                if(projectChooser.choose())

                    mainFrame.setVisible(false);

            }
        });
        JMenuItem item1 = new JMenuItem("Search");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.setPopup();
            }
        });


        JMenuItem item2 = new JMenuItem("Music");

        final boolean[] music = {false};
        var url = System.getProperty("user.dir") + File.separator + "music.wav";
        System.out.println(url);

        URL res = null;
        try {
            res = Paths.get(url).toUri().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(res.getPath());

        Player p = null;
        try {
            p = Manager.createRealizedPlayer(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoPlayerException e) {
            e.printStackTrace();
        } catch (CannotRealizeException e) {
            e.printStackTrace();
        }
        Player finalP = p;
        item2.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                if (!music[0])
                {
                    System.out.println("playing music");
                    music[0] = true;
                    if(finalP!=null)
                        finalP.start();
                }
                else
                {
                    music[0] = false;
                    if(finalP!= null)
                        finalP.stop();
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
        JMenuItem pause = new JMenuItem("Pause");

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reminder.setVisible(true);
            }
        });

        JMenuItem theme = new JMenuItem("Switch Theme");
        theme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.switchTheme();
            }
        });

        menu2.add(pause);
        menu2.add(theme);
        menu2.add(font);
        JPanel pan = new JPanel();
        pan.setBackground(royal_blue);
        JPanel pan2 = new JPanel();
        pan2.setBackground(royal_blue);

        JPanel left = new JPanel();
        add(menu);
        add(menu2);
        add(pan);

        add(pan2);
        MiddleButtons middle = new MiddleButtons();

        add(middle);
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        JPanel pan5 = new JPanel();

        pan3.setBackground(royal_blue);
        pan4.setBackground(royal_blue);
        pan5.setBackground(royal_blue);
        add(pan3);
        add(pan4);
        setBackground(royal_blue);
    }
}
