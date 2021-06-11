package fr.epita.assistants.myide.domain.entity.node;

import fr.epita.assistants.myide.domain.entity.Node;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class File implements Node {
    private Path path;
    private Node parent;
    private final Type type = Types.FILE;
    private final List<@NotNull Node> children = Collections.emptyList();

    public File(Path path, Folder parent) {
        this.path = path;
        this.parent = parent;
    }

    public File(Path path) {
        this.path = path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public Path getPath() {
        return path;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
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
        return true;
    }

    @Override
    public boolean isFolder() {
        return false;
    }
}
