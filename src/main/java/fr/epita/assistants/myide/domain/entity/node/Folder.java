package fr.epita.assistants.myide.domain.entity.node;

import fr.epita.assistants.myide.domain.entity.Node;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Folder implements Node {
    private Path path;
    private final Type type = Types.FOLDER;
    public Node parent;

    public Folder(Path path, List<@NotNull Node> children) {
        this.path = path;
        this.children = children;
    }

    public Folder(Path path, List<@NotNull Node> children, Node parent) {
        this.path = path;
        this.parent = parent;
        this.children = children;
    }
    public void addChild(Node child)
    {

        if (children == null)
            children = new ArrayList<>();
        children.add(child);
        if(child.isFile())
            ((File)child).parent = this;
        else
            ((Folder)child).parent = this;
    }
    public void removeChild(Node child)
    {
        if(children != null)
            children.remove(child);
    }
    @Override
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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
