package fr.epita.assistants.gui;

import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompilePanel extends JMenuBar implements ActionListener {

    public CompilePanel() {
        JMenu menu = new JMenu("File");
        Color royal_blue = new Color(0, 35 , 102);
        setBackground(royal_blue);
        JMenuItem item = new JMenuItem("open");
        JMenuItem item1 = new JMenuItem("git");
        menu.add(item);
        menu.add(item1);
        add(menu);

        JMenu menu2 = new JMenu("Settings");
        JMenuItem color = new JMenuItem("color");
        JMenuItem theme = new JMenuItem("theme");
        menu2.add(color);
        menu2.add(theme);
        JPanel pan = new JPanel();
        pan.setBackground(royal_blue);
        JPanel pan2 = new JPanel();
        pan2.setBackground(royal_blue);



        JButton saveButton = new JButton("Save");
        saveButton.setBackground(royal_blue);
        saveButton.addActionListener(this);

        JButton button = new JButton("Compile");
        button.setBackground(royal_blue);
        //JButton fermer = new JButton("X");
       // fermer.setBackground(royal_blue);
        add(menu2);
        add(pan);
        //add(new JPanel()); //moving compile button a bit to the right
        //add(new JPanel());
        add(pan2);
        add(button);
        //add(fermer);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save"))
        {
            for (var n : IDEConfig.INSTANCE.getNodes())
            {
                var save = new FileOperations(n);
                save.saveText(IDEConfig.INSTANCE.getFrame().getTxt().getText().getText());
            }
        }
    }
}
