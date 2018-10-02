package mainmoviesgrid;

import javafx.concurrent.Service;
import javafx.fxml.Initializable;
import movieobjects.MovieDetails;
import services.GetMovieJsonService;
import services.GetMoviesArrayService;
import utilities.CheckConnection;
import utilities.MovieUriBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MovieDetails[] data;

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (CheckConnection.checkCOnnection()) {
            getMovieJson();
        } else {
            //TODO no connection
        }

    }

    private void getMovieJson() {

        URL url = MovieUriBuilder.buildPopularUri();
        Service<String> service = new GetMovieJsonService(url);
        service.setOnSucceeded(e ->{
            String  response = service.getValue();
            convertResponseToData(response);
        });
        service.restart();

    }

    private void convertResponseToData(String response){
        Service<MovieDetails[]> service = new GetMoviesArrayService(response);
        service.setOnSucceeded(e ->{
            data =  service.getValue();
        });

        service.restart();
    }
}
