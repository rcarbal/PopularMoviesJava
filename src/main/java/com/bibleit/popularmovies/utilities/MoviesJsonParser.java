package utilities;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mainmoviesgrid.MovieDetailsModel;

public class MoviesJsonParser {

    public static MovieDetailsModel[] parseJsonObject(String jsonDataString, String listType){

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(jsonDataString).getAsJsonObject();

        JsonArray allMoviesArray = object.getAsJsonArray("results");
        Gson gson = new Gson();
        MovieDetailsModel[] data = gson.fromJson(allMoviesArray, MovieDetailsModel[].class);
        return data;

    }
}
