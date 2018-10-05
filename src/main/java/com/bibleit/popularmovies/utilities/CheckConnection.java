package utilities;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class CheckConnection {


    public static boolean checkConnection() {

        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
