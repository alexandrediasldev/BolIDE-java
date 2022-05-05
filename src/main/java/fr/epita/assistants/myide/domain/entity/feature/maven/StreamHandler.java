package fr.epita.assistants.myide.domain.entity.feature.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class StreamHandler extends Thread {

    private InputStream inputStream;

    public StreamHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void run(boolean isError) {
        try {
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            final Duration timeout = Duration.ofSeconds(8);
            ExecutorService executor = Executors.newSingleThreadExecutor();

            final Future<String> handler = executor.submit(new Callable() {
                @Override
                public String call() throws Exception {
                    readAll(isError);
                    return "";
                }
            });

            try {
                handler.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("Time Out");
                handler.cancel(true);
            }
            executor.shutdownNow();


        } catch (Exception e) {
            System.err.println(e);
        }


    }
    public void readAll(boolean isError) throws IOException {
        String line = null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);


        while ((line = br.readLine()) != null) {

            if (isError)
                System.err.println("Err:" + line);
            else
                System.out.println(line);
        }

    }
}
