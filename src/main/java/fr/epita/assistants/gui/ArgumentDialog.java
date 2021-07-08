package fr.epita.assistants.gui;

import javax.swing.*;
import java.awt.*;

public class ArgumentDialog {
    String windowName;
    public ArgumentDialog( String windowName)
    {
        this.windowName = windowName;

    }
    public String getArg()
    {
        var s = (String) JOptionPane.showInputDialog(
                IDEConfig.INSTANCE.getFrame(),
                "Argument list:",
                windowName,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                ""
        );
        return s;
    }

}
