package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Node;

import java.util.ArrayList;

public enum IDEConfig {
    INSTANCE;

    private final IDEFrame frame = new IDEFrame();
    private final ArrayList<Node> nodes = new ArrayList<>();

    public IDEFrame getFrame() {
        return frame;
    }

    public void setFont()
    {

    }

    public void setTextSize()
    {

    }

    public void setContent(String content)
    {
        frame.getTxt().getText().setText(content);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
