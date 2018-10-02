package utilities;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import movieobjects.MovieDetails;

public class MoviesJsonParser {

    public static MovieDetails[] parseJsonObject(String jsonDataString, String listType){

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(jsonDataString).getAsJsonObject();

        JsonArray allMoviesArray = object.getAsJsonArray("results");
        Gson gson = new Gson();
        MovieDetails[] data = gson.fromJson(allMoviesArray, MovieDetails[].class);
        return data;

    }
}
