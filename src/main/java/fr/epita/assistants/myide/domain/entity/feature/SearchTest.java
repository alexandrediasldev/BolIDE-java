package fr.epita.assistants.myide.domain.entity.feature;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void execute() {
    }

    @Test
    void addAllFiAles() throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get("./"));
        var c1 = new File(Path.of("./sus.txt"));
        List<Node> l = new ArrayList<>();
        l.add(c1);
        var node = new Folder(Path.of("./"),l);


        Search s =  new Search();
        s.rootDirectory = directory;
        s.analyzer = analyzer;
        s.addAllFiles(node);
    }

    @Test
    void searchFiles() throws IOException, ParseException {

        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get("./"));
        var c1 = new File(Path.of("./sus.txt"));
        List<Node> l = new ArrayList<>();
        l.add(c1);
        var node = new Folder(Path.of("./"),l);


        Search s =  new Search();
        s.rootDirectory = directory;
        s.analyzer = analyzer;
        s.addAllFiles(node);
        var files = s.searchFiles("contents","AMOGUS");
        for (var file : files)
        {
            System.out.println(file.get("filename"));
        }
    }

    @Test
    void addFileToIndex() {
    }

    @Test
    void type() {
    }
}