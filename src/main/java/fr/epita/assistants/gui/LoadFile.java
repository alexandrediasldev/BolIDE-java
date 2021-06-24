package fr.epita.assistants.gui;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.node.File;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.io.IOException;
import java.nio.file.Files;

public class LoadFile {

    private File file;
    private RSyntaxTextArea textArea;

    public LoadFile(Node file, RSyntaxTextArea textArea) {

        if (!(file instanceof File))
            throw new IncompatibleClassChangeError("Node is not a File!");

        this.file = (File) file;
        this.textArea = textArea;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setTextArea(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }

    private String loadNodeContent() {
        String content = "";

        try {
            content = new String(Files.readAllBytes(file.getPath()));
        } catch (IOException e) {
            System.err.println("Failed to load file");
        }

        return content;
    }

    public void loadText() {
        textArea.setText(loadNodeContent());
    }
}
