package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class Toolgit extends JMenu {

    public Toolgit() {

        setIcon(createIcon("src/main/resources/sun.png", 30, 30));
        var amplify = new JMenuItem("amplify");
        amplify.setIcon(createIcon("src/main/resources/plus.png", 20, 20));
        add(amplify);

        var send = new JMenuItem("send");
        send.setIcon(createIcon("src/main/resources/up_right_arrow.png", 20, 20));
        add(send);

        var attract = new JMenuItem("attract");
        attract.setIcon(createIcon("src/main/resources/down_left_arrow.png", 20, 20));
        add(attract);

        var pledge = new JMenuItem("pledge");
        pledge.setIcon(createIcon("src/main/resources/bluecheck.png", 20, 20));
        add(pledge);
    }
}
