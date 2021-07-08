package fr.epita.assistants.gui.editor;

import fr.epita.assistants.gui.IDEConfig;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
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
                System.out.println("Close window" + name);
                timer.stop();
            }
        });
        final SearchPopup[] search = new SearchPopup[1];
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {


                        if (e.isControlDown()) {
                            if (e.getKeyCode() == KeyEvent.VK_S) {
                                System.out.println("SAVED text");
                            } else if (e.getKeyCode() == KeyEvent.VK_W && !timer.isRunning()) {

                                timer.start();


                            }


                        } else if (e.getKeyCode() == KeyEvent.VK_F) {
                            if(search[0] != null)
                                search[0].setVisible(false);
                            search[0] =new SearchPopup();
                            search[0].setVisible(true);
                            System.out.println("Search");
                        }



                        return false;
                    }
                });

    }

    public ArrayList<TextEditor> getTextEditors() {
        return textEditors;
    }

    public RSyntaxTextArea getCurrentText(){
      return textEditors.get(getSelectedIndex()).getText();
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
