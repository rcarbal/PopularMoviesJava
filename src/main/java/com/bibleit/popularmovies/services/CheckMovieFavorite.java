package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CheckMovieFavorite extends Service<Boolean> {
    private String movieId;

    public CheckMovieFavorite(String movieId) {
        this.movieId = movieId;
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                return null;
            }
        };
    }
}
