import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PopularMovies extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("moviesgrid.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(new GridPane()));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
