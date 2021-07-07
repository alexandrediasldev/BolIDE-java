package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Toolmaven extends JMenu {
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
    public Toolmaven() {

        setIcon(createIcon("src/main/resources/macadamia_nut.png", 30, 30));
        var compile = new JMenuItem("compile");
        compile.setIcon(createIcon("src/main/resources/clean.png", 15, 15));
        add(compile);

        var clean = new JMenuItem("clean");
        clean.setIcon(createIcon("src/main/resources/clean.png", 15, 15));
        add(clean);

        var experiment = new JMenuItem("experiment");
        experiment.setIcon(createIcon("src/main/resources/experiment.png", 15, 15));
        add(experiment);

        var wrap = new JMenuItem("wrap");
        wrap.setIcon(createIcon("src/main/resources/wrap.png", 15, 15));
        add(wrap);

        var introduce = new JMenuItem("introduce");
        introduce.setIcon(createIcon("src/main/resources/wrap.png", 15, 15));
        add(introduce);

        var achieve = new JMenuItem("achieve");
        achieve.setIcon(createIcon("src/main/resources/conifer.jpg", 15, 15));
        add(achieve);

        var conifer = new JMenuItem("conifer");
        conifer.setIcon(createIcon("src/main/resources/conifer.jpg", 15, 15));
        add(conifer);

    }
}
