package moviedetailpopup;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @FXML
    public ImageView movie_poster;
    @FXML
    public Pane movie_black_bar;
    @FXML
    public Text movie_title;
    @FXML
    public Text movie_description;
    @FXML
    public StackPane movie_info;

    private ImageView posterImage;

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

        Platform.runLater(() ->{
            cropImage();
            StackPane.setAlignment(movie_black_bar, Pos.BOTTOM_CENTER);
            StackPane.setAlignment(movie_info, Pos.BOTTOM_CENTER);
            movie_title.setText("MOVIE!!!!!!!");
            movie_title.setFill(Color.WHITE);
            movie_title.setFont(Font.font(36));

        });

    };

    private void cropImage(){

        ImageView crop = posterImage;


        movie_poster.setImage(posterImage.getImage());
    }
}
