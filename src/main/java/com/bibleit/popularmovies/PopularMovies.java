import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import movieobjects.MovieDetails;
import services.GetMoviesArrayService;
import utilities.CheckConnection;
import services.GetMovieJsonService;
import utilities.MovieUriBuilder;

import java.net.URL;

public class PopularMovies extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("moviesgrid.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
