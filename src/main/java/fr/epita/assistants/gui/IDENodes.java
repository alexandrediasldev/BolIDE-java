package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Node;
import javax.swing.tree.TreeNode;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

public class IDENodes implements TreeNode {
    private final Node node;
    private final IDENodes parent;
    private final Vector<TreeNode> children =  new Vector<>();

    public IDENodes(Node node){
        this.node = node;
        this.parent = new IDENodes(node.getParent());
        node.getChildren().forEach(n -> children.add(new IDENodes(n, this)));
    }

    public IDENodes(Node node, IDENodes parent)
    {
        this.node = node;
        this.parent = parent;
        node.getChildren().forEach(n -> children.add(new IDENodes(n, this)));
    }

    @Override
    public javax.swing.tree.TreeNode getChildAt(int childIndex) {

        return children.elementAt(childIndex);
    }

    @Override
    public int getChildCount() {
        return node.getChildren().size();
    }

    @Override
    public javax.swing.tree.TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(javax.swing.tree.TreeNode treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return node.isFolder();
    }

    @Override
    public boolean isLeaf() {
        return node.getChildren().isEmpty();
    }

    @Override
    public Enumeration<? extends javax.swing.tree.TreeNode> children() {

        return children.elements();
    }
}
