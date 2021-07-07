package fr.epita.assistants.myide.domain.entity.feature.maven;

import fr.epita.assistants.myide.domain.entity.Project;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MavenExecuter {

    public static int mvnCommand(String command, String project, Object... args) throws IOException, InterruptedException {

        var commands = new ArrayList<String>();
        String str = (String) Arrays.stream(args).toList().get(0);

        commands.add("mvn");
        commands.add(command);
        int i = 0;
        for (var e : str.split(" ")) {

            if(i == 0 && e.toString().startsWith(":")) {
                commands.set(1, commands.get(1) + e.toString());
            }
            else
                if(e.toString() != "" && e.toString() != null && e != null && !e.toString().isBlank()) {
                    commands.add(e.toString());
                }
            i +=1;
        }

        //commands.remove(commands.size()-1);
        System.out.println(commands);
        ProcessBuilder pb = new ProcessBuilder(commands);
        System.out.println(project);
        pb.directory(new File(project));

        if(System.getProperty("os.name").startsWith("Windows"))
            commands.add(0,"powershell");
        Process p = pb.start();

        new StreamHandler(p.getInputStream()).run(false);

        new StreamHandler(p.getErrorStream()).run(true);

        return p.waitFor();

/*
        Runtime runtime = Runtime.getRuntime();
        StringBuilder arguments = new StringBuilder();
        for (var arg : args)
            arguments.append(" " + arg.toString());

        Process process = runtime.exec(command + arguments.toString(), null
                , new File(project));
        new StreamHandler(process.getInputStream()).run(false);

        new StreamHandler(process.getErrorStream()).run(true);


        return process.waitFor();
        */

    }
}
