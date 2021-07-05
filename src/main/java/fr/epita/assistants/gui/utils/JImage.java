package fr.epita.assistants.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JImage extends JPanel {
    private BufferedImage image;
    public JImage(String name) {
            var im = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(name));
            var label = new JLabel(new ImageIcon(im));
            add(label);
    }

}
