import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.CheckConnection;
import utilities.GetMovieJsonService;
import utilities.MovieUriBuilder;

import java.net.URL;

public class PopularMovies extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("moviesgrid.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        if (CheckConnection.checkCOnnection()) {
            getMovieJson();
        } else {
            //TODO no connection
        }


    }

    public static void main(String[] args) {
        launch(args);
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


    }
}
