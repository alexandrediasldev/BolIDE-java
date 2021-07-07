package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Toolgit extends JMenu {
    private ImageIcon createIcon(String Pathname, int width, int height){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);

    }
    public Toolgit() {

        setIcon(createIcon("src/main/resources/macadamia_nut.png", 30, 30));
        var amplify = new JMenuItem("amplify");
        amplify.setIcon(createIcon("src/main/resources/plus.png", 15, 15));
        add(amplify);
    }
}
