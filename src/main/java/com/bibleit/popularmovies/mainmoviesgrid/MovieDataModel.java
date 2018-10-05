package mainmoviesgrid;

import interfaces.MovieDataObserver;
import javafx.concurrent.Service;
import objects.MovieDetails;
import services.GetMovieJsonService;
import services.GetMoviesArrayService;
import utilities.MovieUriBuilder;

import java.net.URL;
import java.util.ArrayList;


public class MovieDataModel {

    private ArrayList<MovieDataObserver> movieDataObservers = new ArrayList<>();

    private MovieDetails[] data;

    public MovieDataModel() {
    }

    public void initializeData() {

        URL url = MovieUriBuilder.buildPopularUri();
        Service<String> service = new GetMovieJsonService(url);
        service.setOnSucceeded(e -> {
            String response = service.getValue();
            convertResponseToData(response);
        });
        service.restart();

    }

    private void convertResponseToData(String response) {
        Service<MovieDetails[]> service = new GetMoviesArrayService(response);
        service.setOnSucceeded(e -> {
            data = service.getValue();
            notifyAllObservers();
        });

        service.restart();
    }

    public void attach(MovieDataObserver movieDataObserver){
        movieDataObservers.add(movieDataObserver);
    }

    public MovieDetails[] getData() {
        return data;
    }

    public void notifyAllObservers(){
        for(MovieDataObserver movieDataObserver : movieDataObservers){
            movieDataObserver.update(data);
        }
    }

}
