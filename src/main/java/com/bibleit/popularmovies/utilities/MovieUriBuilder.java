package utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MovieUriBuilder {

    private static final String mQueryWithAPIKey = "api_key";
    private final static String BASE_POPULAR_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    private final static String BASE_RATED_URL = "http://api.themoviedb.org/3/movie/top_rated";
    private final static String BASE_TRAILER_URL ="http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "80c08a7febb5450bffb919f2c3416264";
    private static final String BASE_VIDEO = "v";

    public static URL buildPopularUri(){

        String uriString = BASE_POPULAR_URL+API_KEY;
        try {
            return new URL(uriString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
