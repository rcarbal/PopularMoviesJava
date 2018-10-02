package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetMovieJsonService extends Service<String> {

    private URL url;

    public GetMovieJsonService(URL url) {
        this.url = url;
    }

    @Override
    protected Task<String> createTask() {

        return new Task<>() {
            @Override
            protected String call() {
                HttpURLConnection urlConnection = null;
                String response = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();

                    Scanner scanner = new Scanner(in);
                    scanner.useDelimiter("\\A");

                    boolean hasInput = scanner.hasNext();
                    if (hasInput) {
                        response = scanner.next();
                    }
                    scanner.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                return response;
            }
        };

    }
}
