package fr.epita.assistants.gui;

import java.awt.*;

public enum IDEConfig {
    INSTANCE;

    private final IDEFrame frame = new IDEFrame();

    public IDEFrame getFrame() {
        return frame;
    }

    public void setFont(String font)
    {
        frame.getTxt().setFont(font);

    }
    public void setTextSize(int textSize)
    {
        frame.getTxt().setTextSize(textSize);
    }

    public void setContent()
    {

    }
}
