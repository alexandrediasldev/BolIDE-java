package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class NodeServiceImplementation implements NodeService {

    @SneakyThrows
    @Override
    public Node update(Node node, int from, int to, byte[] insertedContent) {
        if (!node.isFile()) {
            throw new Exception("Update failure not a file");
        }

        Path path = node.getPath().toAbsolutePath();
        byte[] data = Files.readAllBytes(path);


        try (FileOutputStream fos = new FileOutputStream(path.toString())) {

            for (int i = 0; i < from; ++i) {
                fos.write(data[i]);
            }

            fos.write(insertedContent);

            for (int i = to; i < data.length; ++i) {
                fos.write(data[i]);
            }
        }

        return node;
    }

    @Override
    public boolean delete(Node node) {
        for (var child : node.getChildren())
            delete(child);

        if (node.isFile()) {
            ((File) node).getParent().getChildren().remove(node);
        }
        else {
            ((Folder) node).getParent().getChildren().remove(node);
        }

        return node.getPath().toFile().delete();
    }

    @Override
    public Node create(Node folder, String name, Node.Type type) {

        String path = folder.getPath().toAbsolutePath() +
                java.io.File.separator + name;

        if (type == Node.Types.FILE) {

            File node = new File(Path.of(path),(Folder) folder);
            folder.getChildren().add(node);
            new java.io.File(path);

            return node;

        }

        Folder node = new Folder(Path.of(path), new ArrayList<>(), (Folder) folder);
        folder.getChildren().add(node);
        new java.io.File(path).mkdirs();

        return node;
    }

    @SneakyThrows
    @Override
    public Node move(Node nodeToMove, Node destinationFolder) {
        Path pathToMove = nodeToMove.getPath().toAbsolutePath();
        Path moveTo = Path.of(destinationFolder.getPath().toString() + "/" + nodeToMove.getPath().getFileName().toString());

        ((Folder) destinationFolder).addChild(nodeToMove);
        Folder folderToMove = (Folder) nodeToMove;
        folderToMove.getParent().removeChild(nodeToMove);
        Files.move(pathToMove, moveTo);
        Path p = Path.of(destinationFolder.getPath().toString() + "/" + nodeToMove.getPath().getFileName().toString());
        return new File(p);
    }
}
