package fr.epita.assistants.gui;

import java.awt.*;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import lombok.SneakyThrows;

import javax.swing.*;
import java.util.ArrayList;

public enum IDEConfig {
    INSTANCE;

    private boolean darkmode = true;
    private final IDEFrame frame = new IDEFrame();
    private final ArrayList<Node> nodes = new ArrayList<>();

    public IDEFrame getFrame() {
        return frame;
    }
    public void add()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.ADD, ".");
    }

    public void push()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.PUSH);
    }

    public void pull()
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.PULL);
    }

    public void commit(String msg)
    {
        frame.getP().execute(frame.getCurrentProject(), Mandatory.Features.Git.COMMIT, msg);
    }

    public void setFont(String font)
    {
        frame.getTxt().setFont(font);

    }
    public void setTextSize(int textSize)
    {
        frame.getTxt().setTextSize(textSize);
    }

    public void setContent(String content)
    {
        frame.getTxt().getText().setText(content);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @SneakyThrows
    public void switchTheme()
    {
        if (darkmode) {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            SwingUtilities.updateComponentTreeUI(frame);
            frame.getTxt().switchTheme();
            frame.pack();
            darkmode = false;
        }
        else
        {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
            SwingUtilities.updateComponentTreeUI(frame);
            frame.getTxt().switchTheme();
            frame.pack();
            darkmode = true;
        }
    }
}
