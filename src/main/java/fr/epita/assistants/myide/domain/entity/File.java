package fr.epita.assistants.myide.domain.entity;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class File implements Node{
    private final Path path;
    private final Type type = Types.FILE;
    private final List<@NotNull Node> children = Collections.emptyList();

    public File(Path path) {
        this.path = path;
    }

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
        return true;
    }

    @Override
    public boolean isFolder() {
        return false;
    }
}
