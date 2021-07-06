package fr.epita.assistants.gui.tree;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.gui.utils.FileOperations;
import fr.epita.assistants.myide.domain.entity.Node;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;

public class TreePanel extends JPanel  implements TreeSelectionListener{


   public TreePanel(Node root)
   {

       IDENodes parent = new IDENodes(root);
       JTree treeFile = new JTree(parent);
       treeFile.addTreeSelectionListener(this);
       JScrollPane scrollpane = new JScrollPane();
       scrollpane.getViewport().add(treeFile);
       // check more precisely later
       scrollpane.setPreferredSize(new Dimension(300, 1000));
       add(scrollpane);
   }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        IDENodes node = (IDENodes) e
                .getPath().getLastPathComponent();

        if (node.getNode().isFile())
        {
            var load = new FileOperations(node.getNode());
            //IDEConfig.INSTANCE.getNodes().clear();
            IDEConfig.INSTANCE.getNodes().add(node.getNode());

            load.loadText();
        }
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
