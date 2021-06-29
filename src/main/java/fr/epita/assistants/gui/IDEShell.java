package fr.epita.assistants.gui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import javax.swing.*;
import java.awt.*;

import static com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger.CloseOnEscape;

public class IDEShell extends JPanel {
    public IDEShell()
    {
        JTextArea text = new JTextArea();
        Font font = new Font("ARIAL", Font.PLAIN, 24);
        text.setFont(font);
        text.setPreferredSize(new Dimension(1000,200));
        text.setForeground(Color.lightGray);
        text.setBackground(Color.darkGray);
        add(text);
    }
}
