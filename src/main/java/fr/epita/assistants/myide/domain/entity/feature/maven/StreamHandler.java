package fr.epita.assistants.myide.domain.entity.feature.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHandler extends Thread {

    private InputStream inputStream;

    public StreamHandler(InputStream inputStream) {
        //System.out.println("Setup");
        this.inputStream = inputStream;
    }

    public void run(boolean isError) {
        try {
            //System.out.println("Running");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String line = null;

            while ((line = br.readLine()) != null) {
                if (isError)
                    System.err.println("Err:" + line);
                else
                    System.out.println(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
