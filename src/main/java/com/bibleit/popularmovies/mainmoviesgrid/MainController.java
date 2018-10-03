package mainmoviesgrid;

import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import movieobjects.MovieDetails;
import services.GetMovieJsonService;
import services.GetMoviesArrayService;
import utilities.CheckConnection;
import utilities.MovieUriBuilder;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private double screenHeight;
    private double screenWidth;
    private double screenHeightPadding;
    private double screenWidthPadding;

    private static final String BASE_URL_FOR_IMAGE_PARSING = "http://image.tmdb.org/t/p/w154/";

    @FXML
    private GridPane root;

    private MovieDetails[] data;

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.getHeight();
        screenWidth = screenSize.getWidth();
        screenHeightPadding = screenHeight / 75;
        screenWidthPadding = screenWidth / 25;


        if (CheckConnection.checkCOnnection()) {
            getMovieJson();
        } else {
            //TODO no connection
        }

    }

    private void getMovieJson() {

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
            addToRoot();
        });

        service.restart();
    }

    private void addToRoot() {
        root.setPadding(new Insets(0, screenWidthPadding, 0, screenWidthPadding));
        int index = 0;
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 7; j++) {
                Image image = new Image(BASE_URL_FOR_IMAGE_PARSING+data[index].getPoster_path(), true);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(screenHeight / 3.5);
                imageView.setFitWidth(screenWidth / 8);
                root.add(imageView, j, i);
                index++;
                if (index == 20) {
                    break;
                }
            }
        }
    }
}
