package fr.epita.assistants.myide.domain.entity.feature;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Dist implements Feature {

    private void zipFile(String path) throws IOException {
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

    public void zipFolder(String folder, File file, ZipOutputStream zipOut) throws IOException {
        if (file.isDirectory()) {
            if (!folder.endsWith("/")) {
                folder += "/";
            }
            zipOut.putNextEntry(new ZipEntry(folder));
            zipOut.closeEntry();
            File[] files = file.listFiles();
            for (var child : files) {
                zipFolder(folder + child.getName(), child, zipOut);
            }
        } else {
            var inputStream = new FileInputStream(file);
            var zip = new ZipEntry(folder);
            zipOut.putNextEntry(zip);
            var bytes = new byte[1024];

            for (int length = inputStream.read(bytes); length >= 0; length = inputStream.read(bytes)) {
                zipOut.write(bytes, 0, length);
            }
            inputStream.close();
        }
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
            String path = project.getRootNode().getPath().toString();
            try {
                FileOutputStream outputStream = new FileOutputStream(path + ".zip");
                ZipOutputStream zipOut = new ZipOutputStream(outputStream);
                File file = new File(path);
                zipFolder(file.getName(), file, zipOut);
                zipOut.close();
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Type type() {
        return Mandatory.Features.Any.DIST;
    }
}
