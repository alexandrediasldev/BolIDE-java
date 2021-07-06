import fr.epita.assistants.myide.domain.entity.BasicProject;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.aspect.Any;
import fr.epita.assistants.myide.domain.entity.feature.any.Cleanup;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import fr.epita.assistants.myide.domain.service.NodeServiceImplementation;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class AnyFeatureTest {
    /*
    @SneakyThrows
    void createMyIdeIgnore(String path)
    {
        Files.createFile(Path.of(path + java.io.File.separator + ".myideignore"));
        FileWriter fileWriter = new FileWriter(path + java.io.File.separator + ".myideignore");
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println("test.txt");
        writer.println("trash.txt");

        writer.close();
        fileWriter.close();
    }

    void createMyIdeIgnoreFolder(String path) throws IOException {
        Files.createFile(Path.of(path + java.io.File.separator + ".myideignore"));
        FileWriter fileWriter = new FileWriter(path + java.io.File.separator + ".myideignore");
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println("test.txt");
        writer.println("trash.txt");
        writer.println("foo");

        writer.close();
        fileWriter.close();
    }

    @SneakyThrows
    @Test
    void TestCleanupFromProjectWithFile()
    {
        //.myideignore
        String string = "/home/bastien/Epita/java/PING/src/test/testfiles";
        createMyIdeIgnore(string);

        Cleanup cleanup = new Cleanup();
        NodeServiceImplementation nsi = new NodeServiceImplementation();

        BasicProject project = new BasicProject();
        Folder root = new Folder(Path.of(string));

        Node test = nsi.create(root, "test.txt", Node.Types.FILE);
        Node thrash = nsi.create(root, "trash.txt", Node.Types.FILE);

        root.addChild(test);
        root.addChild(thrash);

        project.setRootNode(root);
        cleanup.execute(project);

        boolean b1 = (! Files.exists(Path.of(string + File.separator + "test.txt")));
        boolean b2 = (! Files.exists(Path.of(string + File.separator + "trash.txt")));

        Files.delete(Path.of(string + File.separator + ".myideignore"));

        assert (b1 && b2);
    }

    @SneakyThrows
    @Test
    void TestCleanupFromProjectWithFolder()
    {
        //.myideignore
        String string = "/home/bastien/Epita/java/PING/src/test/testfiles";
        createMyIdeIgnoreFolder(string);

        Cleanup cleanup = new Cleanup();
        NodeServiceImplementation nsi = new NodeServiceImplementation();

        BasicProject project = new BasicProject();
        Folder root = new Folder(Path.of(string));

        Node test = nsi.create(root, "test.txt", Node.Types.FILE);
        Node thrash = nsi.create(root, "trash.txt", Node.Types.FILE);
        Node foo = nsi.create(root, "foo", Node.Types.FOLDER);

        root.addChild(test);
        root.addChild(thrash);
        root.addChild(foo);

        project.setRootNode(root);
        cleanup.execute(project);

        boolean b1 = (! Files.exists(Path.of(string + File.separator + "test.txt")));
        boolean b2 = (! Files.exists(Path.of(string + File.separator + "trash.txt")));
        boolean b3 = (! Files.exists(Path.of(string + File.separator + "foo/")));

        Files.delete(Path.of(string + File.separator + ".myideignore"));

        assert (b1 && b2 && b3);
    }

     */
}