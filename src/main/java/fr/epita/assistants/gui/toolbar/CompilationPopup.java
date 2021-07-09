package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CompilationPopup extends JDialog {
    public CompilationPopup(boolean type, String msg)
    {
        Icon icon = null;

        String sad = "sad.jpg";
        String happy = "happy.jpg";

        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (type){
            icon = new ImageIcon(image);
            try {
                image = ImageIO.read(getClass().getClassLoader().getResource(happy));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                image = ImageIO.read(getClass().getClassLoader().getResource(sad));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        icon = new ImageIcon(image);
        JLabel j = new JLabel(icon);
        this.add(j);

        this.setTitle(msg);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
        this.setVisible(true);
        this.pack();
    }
}
