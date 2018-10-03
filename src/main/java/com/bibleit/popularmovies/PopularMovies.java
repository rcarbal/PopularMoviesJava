import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PopularMovies extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("moviesgrid.fxml"));
        Parent root = loader.load();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenHeight =  (screenSize.getHeight()) - screenSize.getHeight() * 0.1;
        double screenWidth = (screenSize.getWidth()) - screenSize.getWidth() *0.1;
        stage.setScene(new Scene(root,screenWidth , screenHeight));
        stage.setTitle("Popular Movies");

        Image icon = new Image("icons/black.png");

        stage.getIcons().add(icon);
        stage.centerOnScreen();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
