package fr.epita.assistants.gui.toolbar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class Toolmaven extends JMenu {

    public Toolmaven() {

        setIcon(createIcon("src/main/resources/macadamia_nut.png", 30, 30));

        var clean = new JMenuItem("clean");
        clean.setIcon(createIcon("src/main/resources/clean.png", 20, 20));
        add(clean);

        var compile = new JMenuItem("compile");
        compile.setIcon(createIcon("src/main/resources/cog.png", 20, 20));
        add(compile);



        var experiment = new JMenuItem("experiment");
        experiment.setIcon(createIcon("src/main/resources/experiment.png", 20, 20));
        add(experiment);

        var wrap = new JMenuItem("wrap");
        wrap.setIcon(createIcon("src/main/resources/wrap.png", 20, 20));
        add(wrap);

        var introduce = new JMenuItem("introduce");
        introduce.setIcon(createIcon("src/main/resources/hand.png", 20, 20));
        add(introduce);

        var achieve = new JMenuItem("achieve");
        achieve.setIcon(createIcon("src/main/resources/medal2.png", 20, 20));
        add(achieve);

        var conifer = new JMenuItem("conifer");
        conifer.setIcon(createIcon("src/main/resources/conifer.png", 20, 20));
        add(conifer);

    }
}
