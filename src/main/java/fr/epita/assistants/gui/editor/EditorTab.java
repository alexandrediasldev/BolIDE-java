package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.editor.EditorPane;
import fr.epita.assistants.gui.utils.FileOperations;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class EditorTab implements ActionListener{
    private EditorPane tabpane;
    private String name;


    public EditorTab(EditorPane tabpane, String name) {
        this.tabpane = tabpane;
        this.name = name;
    }

    public EditorTab(EditorPane tabpane, TextEditor textEditor)
    {


        this.tabpane = tabpane;
        this.name = textEditor.getName();
        JPanel pannel = new JPanel();
        JButton exit_button = new JButton("x");
        pannel.add(new JLabel(name));
        pannel.add(exit_button);
        exit_button.addActionListener(this);
        tabpane.addTab(name, textEditor);
        tabpane.setTabComponentAt(tabpane.getIndex(), pannel);

        tabpane.setSelectedIndex(tabpane.getIndex());


    }

    public EditorPane getTabpane() {
        return tabpane;
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        int input = 0;
        if(!IDEConfig.INSTANCE.getSaved())
        {
            input = JOptionPane.showConfirmDialog(IDEConfig.INSTANCE.getFrame(), "Save file before closing?");
            // 0=yes, 1=no, 2=cancel
            if(input == 0) {
                IDEConfig.INSTANCE.setSaved(true);
                for (var n : IDEConfig.INSTANCE.getNodes()) {
                    var save = new FileOperations(n);

                    var editor = IDEConfig.INSTANCE.getTextEditor(String.valueOf(n.getPath().getFileName()));
                    save.saveText(editor.getText().getText());


                }
            }
        }

        IDEConfig.INSTANCE.removeNode(name);
        var index = tabpane.indexOfTab(name);
        tabpane.removeTabAt(index);
        tabpane.removeTab();

    }
}
