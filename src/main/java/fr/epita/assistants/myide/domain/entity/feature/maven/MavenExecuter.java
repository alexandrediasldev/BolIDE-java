package fr.epita.assistants.myide.domain.entity.feature.maven;

import fr.epita.assistants.myide.domain.entity.Project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MavenExecuter {

    public static int mvnCommand(String command, String project, Object... args) throws IOException, InterruptedException {

        Runtime runtime = Runtime.getRuntime();
        StringBuilder arguments = new StringBuilder();
        for (var arg : args)
            arguments.append(" " + arg.toString());

        Process process = runtime.exec(command + arguments.toString(), null
                , new File(project));
        new StreamHandler(process.getInputStream()).run(false);

        new StreamHandler(process.getErrorStream()).run(true);


        return process.waitFor();
    }
}
