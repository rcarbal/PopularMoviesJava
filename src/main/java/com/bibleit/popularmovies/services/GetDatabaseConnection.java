package services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetDatabaseConnection extends Service<Connection> {
    private String movieId;

    public GetDatabaseConnection() {
        this.movieId = movieId;
    }

    @Override
    protected Task<Connection> createTask() {
        return new Task<>() {
            @Override
            protected Connection call() throws Exception {
                return DriverManager.getConnection("jdbc:sqlite:favorite_database.db");
            }
        };
    }
}
