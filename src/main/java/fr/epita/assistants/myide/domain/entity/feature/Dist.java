package fr.epita.assistants.myide.domain.entity.feature;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Dist implements Feature {

    private void zipFile(String path) throws IOException {
        var fileOutputStream = new FileOutputStream(path);
        var outputZip = new ZipOutputStream(fileOutputStream);
        var inputFileStream = new FileInputStream(path);
        var inputZip = new ZipEntry(new File(path).getName());

        outputZip.putNextEntry(inputZip);

        byte[] bytes = new byte[1024];

        for (int length = inputFileStream.read(bytes); length >= 0;)
            outputZip.write(bytes, 0, length);

        outputZip.close();
        inputFileStream.close();
        fileOutputStream.close();
    }

    @Override
    public ExecutionReport execute(Project project, Object... params) {
       Cleanup cleanup = new Cleanup();
       cleanup.execute(project, params);

      if (project.getRootNode().isFile())
      {
          zipFile(project.getRootNode().)
      }
      else
      {

      }
    }

    @Override
    public Type type() {
        return Mandatory.Features.Any.DIST;
    }
}
