package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.node.File;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
        System.out.println(s);
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
        System.out.println(s);
        assertEquals("big",s);
    }
}