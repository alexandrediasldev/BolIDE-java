package fr.epita.assistants.myide.domain.entity;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.List;

public class Folder implements Node{
    private Path path;
    private final Type type = Types.FOLDER;
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
