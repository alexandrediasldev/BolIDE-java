package fr.epita.assistants.myide.domain.entity.node;

import fr.epita.assistants.myide.domain.entity.Node;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Node {
    private Path path;
    private final Type type = Types.FOLDER;
    public Folder parent;

    public Folder(Path path, List<@NotNull Node> children) {
        this.path = path;
        this.children = children;
    }

    public Folder(Path path, List<@NotNull Node> children, Folder parent) {
        this.path = path;
        this.parent = parent;
        this.children = children;
    }

    public Folder(Path root) {
        this.path = root;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {

        if (children == null)
            children = new ArrayList<>();
        children.add(child);
        if (child.isFile())
            ((File) child).setParent(this);
        else
            ((Folder) child).setParent(this);
    }

    public void removeChild(Node child) {
        if (children != null)
            children.remove(child);
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = (Folder) parent;
    }

    private List<@NotNull Node> children;

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<@NotNull Node> getChildren() {
        return children;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isFolder() {
        return true;
    }
}
