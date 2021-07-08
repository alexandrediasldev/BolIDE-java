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
        JMenuItem item1 = new JMenuItem("search");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDEConfig.INSTANCE.setPopup();
            }
        });


        JMenuItem item2 = new JMenuItem("music");

        final boolean[] music = {false};
        var url = System.getProperty("user.dir") + File.separator + "sussy.wav";
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
