package mainmoviesgrid;


import interfaces.Observer;
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
import javafx.stage.StageStyle;
import moviedetailpopup.DetailController;
import objects.MovieDetails;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable, Observer {

    private MovieDataModel model = new MovieDataModel();
    private int currentIndex;
    private double screenHeight;
    private double screenWidth;
    private double screenHeightPadding;
    private double screenWidthPadding;

    private boolean dataReady = false;

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

        model.attach(this);
        model.initializeData();

    }




    private void addToRoot(MovieDetails[] data) {
        root.setPadding(new Insets(0, screenWidthPadding, 0, screenWidthPadding));
        int index = 0;
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 7; j++) {
                Image image = new Image(BASE_URL_FOR_IMAGE_PARSING + data[index].getPoster_path(), true);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(screenHeight / 3.5);
                imageView.setFitWidth(screenWidth / 8);
                imageView.setPickOnBounds(true);
                imageView.setCache(true);
                imageView.setOnMouseClicked(e -> {
                    int row = GridPane.getRowIndex(imageView);
                    int column = GridPane.getColumnIndex(imageView);
                    Image poster = imageView.getImage();
                    currentIndex = getMovieIndex(row, column);
                    openPop(poster, currentIndex);
                });
                root.add(imageView, j, i);
                index++;
                if (index == 20) {
                    break;
                }
            }
        }
    }

    private void openPop(Image poster, int index) {

        if (dataReady){
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
            controller.setMovie(model.getData()[index]);


            Stage popupStage = new Stage();
            Stage parentStage = (Stage) root.getScene().getWindow();
            root.setEffect(new GaussianBlur());

            popupStage.initOwner(parentStage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.centerOnScreen();
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.setScene(new Scene(rootParent));
//        popupStage.setOnCloseRequest(e -> {
//            root.setEffect(null);
//        });
            parentStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) ->{
                if (isNowFocused){
                    root.setEffect(null);
                }
            });
            popupStage.show();

        }else {

        }

    }

    private int getMovieIndex(int row, int column) {
        if (row == 0) {
            return column;
        } else if (row == 1) {
            return 7 + column;
        } else {
            return 2 * 7 + column;
        }
    }

    @Override
    public void update(MovieDetails[] details) {
        addToRoot(details);
        dataReady = true;

    }
}
