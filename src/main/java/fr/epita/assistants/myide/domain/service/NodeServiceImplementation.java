package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;

import java.nio.file.Path;
import java.util.ArrayList;

public class NodeServiceImplementation implements NodeService {

    @Override
    public Node update(Node node, int from, int to, byte[] insertedContent) {
        return null;
    }

    @Override
    public boolean delete(Node node) {
        for (var child : node.getChildren())
            delete(child);

        node.getParent().getChildren().remove(node);

        return node.getPath().toFile().delete();
    }

    @Override
    public Node create(Node folder, String name, Node.Type type) {

        String path = folder.getPath().toAbsolutePath() +
                java.io.File.separator + name;

        if (type == Node.Types.FILE) {

            File node = new File(Path.of(path), folder);
            folder.getChildren().add(node);
            new java.io.File(path);

            return node;

        }

        Folder node = new Folder(Path.of(path), new ArrayList<>(), folder);
        folder.getChildren().add(node);
        new java.io.File(path).mkdirs();

        return node;
    }

    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {
        return null;
    }
}
