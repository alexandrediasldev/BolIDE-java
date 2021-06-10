package fr.epita.assistants.myide.domain.entity.node;

import fr.epita.assistants.myide.domain.entity.Node;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.List;

public class Folder implements Node {
    private Path path;
    private final Type type = Types.FOLDER;
    private Node parent;

    public Folder(Path path, List<@NotNull Node> children) {
        this.path = path;
        this.children = children;
    }

    public Folder(Path path, List<@NotNull Node> children, Node parent) {
        this.path = path;
        this.parent = parent;
        this.children = children;
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
