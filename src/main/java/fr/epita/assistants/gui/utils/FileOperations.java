package fr.epita.assistants.gui.utils;

import fr.epita.assistants.gui.IDEConfig;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import fr.epita.assistants.myide.domain.service.NodeServiceImplementation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileOperations {

    private File file;
    private String content = "";

    public FileOperations(Node file) {

        if (!(file instanceof File))
            throw new IncompatibleClassChangeError("Node is not a File!");

        this.file = (File) file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private String loadNodeContent() {
        try {
            content = new String(Files.readAllBytes(file.getPath()));
        } catch (IOException e) {
            System.err.println("Failed to load file");
        }

        return content;
    }

    public void loadText() {
        IDEConfig.INSTANCE.setContent(loadNodeContent(), String.valueOf(file.getPath().getFileName()));
    }

    public void saveText(String content) throws IOException {
        var path = file.getPath().toAbsolutePath();
        Files.writeString(path, content);
    }
}
