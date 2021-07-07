package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Toolgit extends JMenu {
    public Toolgit() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/macadamia_nut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var dimg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(dimg));
    }
}
