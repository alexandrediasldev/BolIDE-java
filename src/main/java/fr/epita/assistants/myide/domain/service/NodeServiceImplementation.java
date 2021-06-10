package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NodeServiceImplementation implements NodeService {

    @Override
    public Node update(Node node, int from, int to, byte[] insertedContent) {
        return null;
    }

    @Override
    public boolean delete(Node node) {
        return false;
    }

    @Override
    public Node create(Node folder, String name, Node.Type type) {

        String path = folder.getPath().toAbsolutePath() +
                java.io.File.separator + name;

        if (type == Node.Types.FILE) {

            File node = new File(Path.of(path));
            folder.getChildren().add(node);
            new java.io.File(path);

            return node;

        }

            Folder node = new Folder(Path.of(path), new ArrayList<>());
            folder.getChildren().add(node);
            new java.io.File(path).mkdirs();

            return node;
    }

    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {
        return null;
    }
}
