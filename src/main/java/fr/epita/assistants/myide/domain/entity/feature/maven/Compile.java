package fr.epita.assistants.myide.domain.entity.feature.maven;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.*;

public class Compile implements Feature {
    private InputStream stream;

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
            var command = "mvn compile";
            var exe = MavenExecuter.mvnCommand(command, project.getRootNode().toString()
                    , params);
            if (exe != 0) {
                return report;
            }
        } catch (IOException | InterruptedException e) {
            return report;
        }


        report.setSuccess(true);
        return report;
    }

    @Override
    public Type type() {
        return Mandatory.Features.Maven.COMPILE;
    }
}
