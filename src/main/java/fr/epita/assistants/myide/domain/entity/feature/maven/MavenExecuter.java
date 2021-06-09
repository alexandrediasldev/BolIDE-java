package fr.epita.assistants.myide.domain.entity.feature.maven;

import java.io.IOException;
import java.util.Arrays;

public  class MavenExecuter {
    public static int mvnCommand(String command, String ...args) throws IOException, InterruptedException
    {

        Runtime runtime = Runtime.getRuntime();

        Process process = runtime.exec(command+ " " +String.join(" ", args));
        /* Lancement du thread de récupération de la sortie standard */
        new StreamHandler(process.getInputStream()).run(false);

        /* Lancement du thread de récupération de la sortie en erreur */
        new StreamHandler(process.getErrorStream()).run(true);


        return process.waitFor();
    }
}
