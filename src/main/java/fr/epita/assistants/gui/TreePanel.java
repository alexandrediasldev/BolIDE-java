package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import fr.epita.assistants.myide.domain.service.ProjectServiceImplementation;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class TreePanel extends JPanel {


   public TreePanel(Node root)
   {

       IDENodes parent = new IDENodes(root);
       JTree treeFile = new JTree(parent);
       treeFile.addTreeSelectionListener(new TreeSelectionListener() {
           public void valueChanged(TreeSelectionEvent e) {
               IDENodes node = (IDENodes) e
                       .getPath().getLastPathComponent();

               if (node.getNode().isFile())
               {
                   var load = new LoadFile(node.getNode(), IDEFrame.getText());
                   load.loadText();
               }

           }
       });
       JScrollPane scrollpane = new JScrollPane();
       scrollpane.getViewport().add(treeFile);
       // check more precisely later
       scrollpane.setPreferredSize(new Dimension(300, 1000));
       add(scrollpane);
   }



   /*
    public static void main(String[] args) {

        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.add(panel, BorderLayout.WEST);
        frame.setVisible(true);
    }
    */
}
