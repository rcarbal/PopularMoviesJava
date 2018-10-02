package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import movieobjects.MovieDetails;
import utilities.MoviesJsonParser;

public class GetMoviesArrayService extends Service<MovieDetails[]> {
    private String jsonString;

    public GetMoviesArrayService(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    protected Task<MovieDetails[]> createTask() {
       return new Task<>() {
            @Override
            protected MovieDetails[] call() throws Exception {
                return MoviesJsonParser.parseJsonObject(jsonString, null);
            }
        };
    }
}
