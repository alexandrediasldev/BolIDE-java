import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import fr.epita.assistants.myide.domain.service.NodeServiceImplementation;
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
    void move() throws IOException {
        NodeServiceImplementation nodeService = new NodeServiceImplementation();
        Path p1 = Path.of("./move.txt");
        Path p2 = Path.of("./foldermove/");


        File f1 = new File(p1);
        List l2 = new ArrayList();
        l2.add(f1);
        Folder f2 = new Folder(p2,null);
        l2.add(f2);
        Folder root = new Folder(Path.of("./"),l2);
        var n = nodeService.move(f1, f2);
        nodeService.move(n,root);



    }
}