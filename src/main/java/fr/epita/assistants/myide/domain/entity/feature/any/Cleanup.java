package fr.epita.assistants.myide.domain.entity.feature.any;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Cleanup implements Feature {
    boolean deleteDirectory(File file){
        File[] contents = file.listFiles();

        if (contents != null)
            for (File f : contents)
                deleteDirectory(f);

        return file.delete();
    }
    @Override
    public ExecutionReport execute(Project project, Object... params) {

        class Report implements ExecutionReport
        {
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

        try(Stream<String> stream = Files.lines(Paths.get(".myideignore"))){
            stream.forEach(file -> {
               File f = new File(file);

               if (! f.isDirectory()) {
                   f.delete();
               }
               else {
                   deleteDirectory(f);
               }
           });
        } catch (IOException e) {
            return report;
        }

        report.setSuccess(true);
        return report;
    }

    @Override
    public Type type() {
        return Mandatory.Features.Any.CLEANUP;
    }
}
