package fr.epita.assistants.myide.domain.entity;


import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.List;

public interface Node {

    /**
     * @return The Node path.
     */
    @NotNull Path getPath();

    /**
     * @return The Node parent.
     */
    @NotNull Node getParent();


    /**
     * @return The Node type.
     */
    @NotNull Type getType();

    /**
     * If the Node is a Folder, returns a list of its children,
     * else returns an empty list.
     *
     * @return List of node
     */
    @NotNull List<@NotNull Node> getChildren();

    default boolean isFile() {
        return getType().equals(Types.FILE);
    }

    default boolean isFolder() {
        return getType().equals(Types.FOLDER);
    }

    interface Type {

    }

    enum Types implements Type {
        FILE,
        FOLDER
    }
}
