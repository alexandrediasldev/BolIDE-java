package fr.epita.assistants.gui.utils;

import fr.epita.assistants.gui.IDEConfig;

import javax.swing.*;
import java.io.File;

public class JProjectChooser {
    JFileChooser fileChooser = new JFileChooser();
    JFrame mainPanel;
    public JProjectChooser(JFrame mainPanel)
    {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.mainPanel = mainPanel;

    }
    public boolean choose()
    {
        int option = fileChooser.showOpenDialog(mainPanel);
        if(option == JFileChooser.APPROVE_OPTION){

            File file = fileChooser.getSelectedFile();
            IDEConfig.INSTANCE.createFrame(file.getPath());
            IDEConfig.INSTANCE.getFrame();

            return true;
        }
        return false;
    }

}
