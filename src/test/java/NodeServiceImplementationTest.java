import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import fr.epita.assistants.myide.domain.service.NodeServiceImplementation;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


class NodeServiceImplementationTest {

    @Test
    void update() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();
        Path path = Path.of("update.txt");
        File f = new File(path);
        try {
            FileWriter myWriter = new FileWriter("update.txt");
            myWriter.write("test test test");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = "updated".getBytes();
        nodeService.update(f,3,6,b);
        byte[] data = Files.readAllBytes(path);
        String s = new String(data, StandardCharsets.UTF_8);;

        assertEquals("tesupdatedest test",s);
    }

    @Test
    void update2() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();
        Path path = Path.of("update.txt");
        File f = new File(path);
        try {
            FileWriter myWriter = new FileWriter("update.txt");
            myWriter.write("a");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = "big".getBytes();
        nodeService.update(f,0,1, b);
        byte[] data = Files.readAllBytes(path);
        String s = new String(data, StandardCharsets.UTF_8);;

        assertEquals("big",s);
    }


    @Test
    void moveFileToFile() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();

        Path p1 = Path.of("./testfiles/fileToMove.txt");
        Path p2 = Path.of("./testfiles/MoveHere.txt");
        Path proot = Path.of("./testfiles/");

        Files.createFile(p1);

        Folder root = new Folder(proot);
        File fileToMove = new File(p1,root);
        File MoveHere = new File(p2,root);


        var n = nodeService.move(fileToMove, MoveHere);
        assert(Files.exists(p2));
        Files.delete(p2);
    }
    @Test
    void moveFileToDirectory() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();

        Path p1 = Path.of("./testfiles/fileToMove.txt");
        Path p2 = Path.of("./testfiles/dir/");
        Path p3 = Path.of("./testfiles/dir/fileToMove.txt");
        Path proot = Path.of("./testfiles/");

        Files.createFile(p1);
        Files.createDirectory(p2);

        Folder root = new Folder(proot);
        File fileToMove = new File(p1,root);
        Folder MoveHere = new Folder(p2,root);


        var n = nodeService.move(fileToMove, MoveHere);
        assert(Files.exists(p3));
        Files.delete(p3);
        Files.delete(p2);
    }
    @Test
    void moveDirectoryToDirectory() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();

        Path p1 = Path.of("./testfiles/dir1");
        Path p2 = Path.of("./testfiles/dir2/");
        Path p3 = Path.of("./testfiles/dir2/dir1/");
        Path proot = Path.of("./testfiles/");

        if(!Files.exists(p1))
            new java.io.File(String.valueOf(p1)).mkdirs();
        if(!Files.exists(p2))
            new java.io.File(String.valueOf(p2)).mkdirs();
        if(Files.exists(p3))
            Files.delete(p3);


        Folder root = new Folder(proot);
        Folder fileToMove = new Folder(p1,root);
        Folder MoveHere = new Folder(p2,root);


        var n = nodeService.move(fileToMove, MoveHere);
        boolean d = Files.exists(p3);

        Files.delete(p3);
        Files.delete(p2);
        assert (d);
    }
    @Test
    void createNodeTestFile() throws IOException {

        Path proot = Path.of("./testfiles/");

        Node.Type type = Node.Types.FILE;
        String name = "TestFile.txt";
        Folder root = new Folder(proot);

        NodeServiceImplementation nodeServiceImplementation = new NodeServiceImplementation();
        var new_node = nodeServiceImplementation.create(root, name, type);
        boolean b = Files.exists(new_node.getPath());

        Files.delete(new_node.getPath());

        assert(b);
    }

    @Test
    void createNodeTestFolder() throws IOException {

        Path proot = Path.of("./testfiles/");

        Node.Type type = Node.Types.FOLDER;
        String name = "dirTest/";
        Folder root = new Folder(proot);

        if(Files.exists(Path.of(name)))
            Files.delete(Path.of(name));

        NodeServiceImplementation nodeServiceImplementation = new NodeServiceImplementation();
        var new_node = nodeServiceImplementation.create(root, name, type);
        boolean b = Files.exists(new_node.getPath());

        Files.delete(new_node.getPath());

        assert(b);
    }

    @SneakyThrows
    @Test
    void deleteNodeMouliTest(){
        NodeServiceImplementation nsi = new NodeServiceImplementation();
        Path proot = Path.of("./testfiles/");
        Folder root = new Folder(proot);
        Node.Type type1 = Node.Types.FOLDER;
        String name = "dir/";

        Node.Type type2 = Node.Types.FILE;
        String name2 = "file";

        if(Files.exists(Path.of("./testfiles/dir/"+name2)))
            Files.delete(Path.of("./testfiles/dir/" + name2));


        if(Files.exists(Path.of("./testfiles/"+name)))
            Files.delete(Path.of("./testfiles/" + name));


        Node new_node = nsi.create(root, name, type1);
        Node new_file = nsi.create(new_node, name2,type2);

        nsi.delete(new_node);
        assert(!Files.exists(Path.of("./testfiles/dir/"+name2)));

    }
}