package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import mainmoviesgrid.MovieDetailsModel;
import utilities.MoviesJsonParser;

public class GetMoviesArrayService extends Service<MovieDetailsModel[]> {
    private String jsonString;

    public GetMoviesArrayService(String jsonString) {
        this.jsonString = jsonString;
    }

    @Override
    protected Task<MovieDetailsModel[]> createTask() {
       return new Task<>() {
            @Override
            protected MovieDetailsModel[] call() throws Exception {
                return MoviesJsonParser.parseJsonObject(jsonString, null);
            }
        };
    }
}
