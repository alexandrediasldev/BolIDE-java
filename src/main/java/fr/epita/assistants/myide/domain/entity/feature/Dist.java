package fr.epita.assistants.myide.domain.entity.feature;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Dist implements Feature {

    public void zipFile(String path) throws IOException {
        var file = new File(path);
        var fileOutputStream = new FileOutputStream(path + ".zip");
        var outputZip = new ZipOutputStream(fileOutputStream);
        var inputFileStream = new FileInputStream(file);
        var inputZip = new ZipEntry(file.getName());

        outputZip.putNextEntry(inputZip);

        byte[] bytes = new byte[1024];

        for (int length = inputFileStream.read(bytes); length >= 0; length = inputFileStream.read(bytes))
            outputZip.write(bytes, 0, length);

        outputZip.close();
        inputFileStream.close();
        fileOutputStream.close();
    }

    @Override
    public ExecutionReport execute(Project project, Object... params) {
        Cleanup cleanup = new Cleanup();
        cleanup.execute(project, params);

        if (project.getRootNode().isFile()) {
            try {
                zipFile(project.getRootNode().getPath().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //zipFile(project.getRootNode().getPath().toString());
        }
        return null;
    }

    @Override
    public Type type() {
        return Mandatory.Features.Any.DIST;
    }
}
