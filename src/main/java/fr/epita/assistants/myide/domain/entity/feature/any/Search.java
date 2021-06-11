package fr.epita.assistants.myide.domain.entity.feature.any;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.*;
import java.util.stream.Collectors;

public class Search implements Feature {
    public Directory rootDirectory = null;
    public StandardAnalyzer analyzer = null;
    public Search()
    {

    }

    @Override
    public ExecutionReport execute(Project project, Object... params) {

        class Report implements ExecutionReport {
            private boolean success;

            public Report(boolean success) {
                this.success = success;
            }

            @Override
            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }

        Report report = new Report(false);
        try {
            analyzer = new StandardAnalyzer();
            rootDirectory = FSDirectory.open(project.getRootNode().getPath());
            var node = project.getRootNode();

            addAllFiles(node);
            for (var param : params) {
                var listDoc = searchFiles("content", param.toString());
                if (!listDoc.isEmpty()) {
                    System.out.println(param.toString() + "found in :\n");
                    for (var doc : listDoc)
                        System.out.println(doc.get("path") + "\n");
                }
            }


        } catch (IOException | ParseException e) {
            return report;
        }

        report.setSuccess(true);

        return report;
    }

    public void addAllFiles(Node n) throws IOException {
        if (n.isFile())
            addFileToIndex(n.getPath().toString());
        else
            for (var a : n.getChildren())
                addAllFiles(a);
    }

    public List<Document> searchFiles(String inField, String queryString) throws ParseException, IOException {
        Query query = new QueryParser(inField, analyzer)
                .parse(queryString);
        IndexReader rootReader = DirectoryReader.open(rootDirectory);
        IndexSearcher searcher = new IndexSearcher(rootReader);
        TopDocs topDocs = searcher.search(query, 10);

        var a = Arrays.stream(topDocs.scoreDocs).map(scoreDoc -> {
            try {
                var s = searcher.doc(scoreDoc.doc);
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        })
                .distinct().collect(Collectors.toList());
        return a;


    }

    public void addFileToIndex(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        File file = path.toFile();
        //writer
        IndexWriterConfig indexWriterConfig
                = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(
                rootDirectory, indexWriterConfig);


        Document document = new Document();

        FileReader fileReader = new FileReader(file);
        document.add(
                new TextField("contents",
                        fileReader));
        document.add(
                new StringField("path", file.getPath(), Field.Store.YES));
        document.add(
                new StringField("filename", file.getName(), Field.Store.YES));

        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Override
    public Type type() {
        return Mandatory.Features.Any.SEARCH;
    }
}
