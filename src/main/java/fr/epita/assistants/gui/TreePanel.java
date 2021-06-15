package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import fr.epita.assistants.myide.domain.service.ProjectServiceImplementation;

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
               System.out.println(node);
           }
       });
       JScrollPane scrollpane = new JScrollPane();
       scrollpane.getViewport().add(treeFile);
       add(BorderLayout.CENTER, scrollpane);
   }

    public static void main(String[] args) {
        ProjectServiceImplementation p = new ProjectServiceImplementation();
        var project = p.load(Path.of("./"));
        IDEFrame frame = new IDEFrame();
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);

        frame.add(new TreePanel(project.getRootNode()), BorderLayout.WEST);
        frame.setVisible(true);
    }
}
