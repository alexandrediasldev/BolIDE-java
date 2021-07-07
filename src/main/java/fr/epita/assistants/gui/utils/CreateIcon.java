package fr.epita.assistants.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateIcon {

    static public ImageIcon createIcon(String Pathname, int width, int height){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        var dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);

    }
}
