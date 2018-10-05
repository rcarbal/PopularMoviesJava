package moviedetailpopup;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import objects.MovieDetails;
import utilities.CheckConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    private static final String BASE_URL_FOR_IMAGE_PARSING = "http://image.tmdb.org/t/p/w500/";

    @FXML
    public ImageView movie_poster;
    @FXML
    public Pane movie_black_bar;
    @FXML
    public Text movie_title;
    @FXML
    public Text movie_description;
    @FXML
    public StackPane movie_info_pane;
    @FXML
    public HBox top_bar;
    @FXML
    public VBox movie_details_vbox;
    @FXML
    public Button favorite_button;
    @FXML
    public Button close_button;
    @FXML
    public StackPane top_background;
    @FXML
    public VBox root;
    @FXML
    public Pane mainPane;

    private ImageView posterImage;

    public MovieDetails getMovie() {
        return movie;
    }

    public void setMovie(MovieDetails movie) {
        this.movie = movie;
    }

    private MovieDetails movie;

    public ImageView getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(ImageView posterImage) {
        this.posterImage = posterImage;
    }
//
//    public DetailController(ImageView posterImage) {
//        this.posterImage = posterImage;
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(this::setupLayout);

    };

    private void setupLayout(){
        close_button.setStyle("-fx-background-color: transparent");
        favorite_button.setStyle("-fx-background-color: transparent");

        movie_poster.setImage(posterImage.getImage());

        StackPane.setAlignment(movie_black_bar, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(movie_info_pane, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(top_background, Pos.TOP_CENTER);
        StackPane.setAlignment(top_bar, Pos.TOP_CENTER);

        movie_title.setText(movie.getTitle());
        movie_title.setFill(Color.WHITE);
        movie_title.setFont(Font.font(36));
        movie_title.wrappingWidthProperty().bind(movie_info_pane.widthProperty());
        movie_title.setTextAlignment(TextAlignment.CENTER);

        movie_description.setText(movie.getOverview());
        movie_description.setFill(Color.WHITE);
        movie_description.setFont(Font.font(16));
        movie_description.setTextAlignment(TextAlignment.CENTER);

        Image closeImage = new Image("icons/close.png");
        ImageView closeImageView = new ImageView(closeImage);
        closeImageView.setFitHeight(30);
        closeImageView.setFitWidth(30);
        close_button.setGraphic(closeImageView);
        close_button.setOnMouseClicked(e ->{
            Stage stage = (Stage) close_button.getScene().getWindow();
            stage.close();
        });
        Image notFavorite = new Image("icons/notfavorite.png");
        ImageView notFavoriteImageView = new ImageView(notFavorite);
        favorite_button.setGraphic(notFavoriteImageView);

        mainPane.setVisible(false);
        getHighResImage();

    }

    private void getHighResImage(){
        if (CheckConnection.checkConnection()){
            Image highImage = new Image(BASE_URL_FOR_IMAGE_PARSING + movie.getPoster_path(), true);
            movie_poster.setImage(highImage);
        }
    }
}
