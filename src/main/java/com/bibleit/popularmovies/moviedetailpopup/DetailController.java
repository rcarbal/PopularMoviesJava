package moviedetailpopup;

import interfaces.DatabaseConnectObserver;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailController implements Initializable, DatabaseConnectObserver {

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
    private DetailModel model;

    public void setMovie(MovieDetails movie) {
        this.movie = movie;
    }

    private MovieDetails movie;

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


    }

    private void setLayoutInvisible() {

        close_button.setVisible(false);
        favorite_button.setVisible(false);
        movie_poster.setVisible(false);
        movie_black_bar.setVisible(false);
        movie_info_pane.setVisible(false);
        top_bar.setVisible(false);
        movie_title.setVisible(false);
        movie_description.setVisible(false);
        close_button.setVisible(false);
        favorite_button.setVisible(false);

        setupLayout();
    }


    private void setupLayout() {

        close_button.setStyle("-fx-background-color: transparent");
        favorite_button.setStyle("-fx-background-color: transparent");

        movie_poster.setImage(posterImage.getImage());
        mainPane.setVisible(false);
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
        close_button.setOnMouseClicked(e -> {
            Stage stage = (Stage) close_button.getScene().getWindow();
            stage.close();
        });

        //By default set image to not favorite.
        Image notFavorite = new Image("icons/notfavorite.png");
        ImageView notFavoriteImageView = new ImageView(notFavorite);
        favorite_button.setGraphic(notFavoriteImageView);
        favorite_button.setOnMouseClicked(null);

        getHighResImage();

        //Connect to database
        connectToDatabase();
    }

    private void getHighResImage() {
        if (CheckConnection.checkConnection()) {
            Image highImage = new Image(BASE_URL_FOR_IMAGE_PARSING + movie.getPoster_path(), true);
            movie_poster.setImage(highImage);
        }
    }

    private void connectToDatabase() {
        model = new DetailModel();
        model.attach(this);
        try {
            model.connectDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int setAsFavorite(int movieId) {
        if (model == null) {
            connectToDatabase();
        }
        try {
            movie.setmFavorite(true);
            return model.addMovie(movieId);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void removeFromFavorites(int id) {
        if (model == null){
            connectToDatabase();
        }

        int rowsRemoved = model.removeMovie(movie.getId());
    }

    @Override
    public void update(boolean connected) {

        //Check the database and change icon if movie is favorite.
        int index = model.isFavorite(movie.getId());
        if (index > 0) {
            Image image = new Image("icons/favorite.png");
            favorite_button.setGraphic(new ImageView(image));
            if (!movie.getFavorite()){
                movie.setmFavorite(true);
            }

        }
        enableFavoriteButtonFunction();
    }


        private void enableFavoriteButtonFunction () {

            //When clicked
            favorite_button.setOnMouseClicked(e -> {

                if (!movie.getFavorite()) {
                    int index = setAsFavorite(movie.getId());
                    if (index > 0) {
                        Image favorite = new Image("icons/favorite.png");
                        ImageView favoriteImageView = new ImageView(favorite);
                        favorite_button.setGraphic(favoriteImageView);
                    }
                }else if (movie.getFavorite()){
                    removeFromFavorites(movie.getId());
                    movie.setmFavorite(false);
                    Image favorite = new Image("icons/notfavorite.png");
                    ImageView favoriteImageView = new ImageView(favorite);
                    favorite_button.setGraphic(favoriteImageView);
                }
            });

        }
    }

