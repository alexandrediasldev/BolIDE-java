package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.utils.FileOperations;
import lombok.SneakyThrows;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EditorPane extends JTabbedPane {

    private int index = 0;
    ArrayList<TextEditor> textEditors = new ArrayList<>();
    Timer timer = null;
    public EditorPane() {
        //setLayout(new BorderLayout()); //Here
        index = 0;
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var index = getSelectedIndex();
                if(index<0) {
                    timer.stop();
                    return;
                }
                var editor = textEditors.get(index);

                var name = editor.getName();
                IDEConfig.INSTANCE.removeNode(name);
                removeTabAt(index);
                removeTab();
                //System.out.println("Close window" + name);
                timer.stop();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @SneakyThrows
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {


                        if (e.isControlDown()) {
                            if (e.getKeyCode() == KeyEvent.VK_S) {

                                for (var n : IDEConfig.INSTANCE.getNodes())
                                {
                                    var save = new FileOperations(n);

                                    var editor = IDEConfig.INSTANCE.getTextEditor(String.valueOf(n.getPath().getFileName()));
                                    save.saveText(editor.getText().getText());

                                }
                            } else if (e.getKeyCode() == KeyEvent.VK_W && !timer.isRunning()) {

                                timer.start();


                            } else if (e.getKeyCode() == KeyEvent.VK_F) {
                                IDEConfig.INSTANCE.setPopup();

                            }
                        }



                        return false;
                    }
                });


        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(IDEConfig.INSTANCE.getPopup() != null && IDEConfig.INSTANCE.getPopup().isVisible())
                    IDEConfig.INSTANCE.setPopup();
            }
        });

    }

    public ArrayList<TextEditor> getTextEditors() {
        return textEditors;
    }

    public RSyntaxTextArea getCurrentText(){
        var index = getSelectedIndex();
        if(index == -1)
            return null;
      return textEditors.get(index).getText();
    }

    public int getIndex() {
        return index;
    }

    public void addPane(TextEditor editor)
    {
        textEditors.add(editor);
        EditorTab tab = new EditorTab(this, editor);
        index++;
    }
    public void removeTab()
    {
        textEditors.remove(index-1);
        index--;
    }


}
