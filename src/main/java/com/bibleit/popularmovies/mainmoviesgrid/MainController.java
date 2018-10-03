package mainmoviesgrid;

import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviedetailpopup.DetailController;
import services.GetMovieJsonService;
import services.GetMoviesArrayService;
import utilities.MovieUriBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MovieDetailsModel[] data;

    private double screenHeight;
    private double screenWidth;
    private double screenHeightPadding;
    private double screenWidthPadding;

    private static final String BASE_URL_FOR_IMAGE_PARSING = "http://image.tmdb.org/t/p/w154/";

    @FXML
    private GridPane root;


    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = screenSize.getHeight();
        screenWidth = screenSize.getWidth();
        screenHeightPadding = screenHeight / 75;
        screenWidthPadding = screenWidth / 25;


//        if (CheckConnection.checkCOnnection()) {
            getMovieJson();
//        } else {
            //TODO no connection
//        }
//
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
        Service<MovieDetailsModel[]> service = new GetMoviesArrayService(response);
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
                imageView.setPickOnBounds(true);
                imageView.setCache(true);
                imageView.setOnMouseClicked(e -> {
                   int row = GridPane.getRowIndex(imageView);
                    int column = GridPane.getColumnIndex(imageView);
                    Image poster = imageView.getImage();
                    openPop(poster);
                });
                root.add(imageView, j, i);
                index++;
                if (index == 20) {
                    break;
                }
            }
        }
    }

    private void openPop(Image poster){

        ImageView moviePoster = new ImageView(poster);

        FXMLLoader detailLoader = new FXMLLoader();
        detailLoader.setLocation(getClass().getResource("/detailview.fxml"));

        Parent rootParent = null;
        try {
            rootParent = detailLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DetailController controller = detailLoader.<DetailController>getController();
        controller.setPosterImage(moviePoster);


        Stage popupStage = new Stage();
        Stage parentStage = (Stage) root.getScene().getWindow();
        root.setEffect(new GaussianBlur());

        popupStage.initOwner(parentStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.centerOnScreen();
        popupStage.setScene(new Scene(rootParent));
        popupStage.setOnCloseRequest(e -> {
            root.setEffect(null);
        });
//        popupStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) ->{
//            if (! isNowFocused){
//                parentStage.isFocused();
//                popupStage.hide();
//                root.setEffect(null);
//            }
//        });
        popupStage.show();

    }
}
