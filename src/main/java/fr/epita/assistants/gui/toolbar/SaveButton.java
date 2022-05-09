package fr.epita.assistants.gui.toolbar;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.utils.FileOperations;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static fr.epita.assistants.gui.utils.CreateIcon.createIcon;

public class SaveButton extends JButton implements ActionListener {
    public SaveButton()
    {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        setMaximumSize(new Dimension(35,35));
        setBorder(emptyBorder);
        setIcon(createIcon("floppy.png", 30, 30));
        addActionListener(this);
    }
    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
            IDEConfig.INSTANCE.setSaved(true);
            for (var n : IDEConfig.INSTANCE.getNodes())
            {
                var save = new FileOperations(n);

                var editor = IDEConfig.INSTANCE.getTextEditor(String.valueOf(n.getPath().getFileName()));
                save.saveText(editor.getText().getText());


            }


    }

}
