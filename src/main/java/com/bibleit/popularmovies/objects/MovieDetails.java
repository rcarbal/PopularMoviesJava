package objects;

import javafx.scene.image.ImageView;

public class MovieDetails {


    private int id =-1;
    private String poster_path ="";
    private String title="";
    private String release_date="";
    private String movieLength="";
    private double vote_average= 0.0;
    private String overview="";
    private boolean mFavorite;
    private long databaseColumn=-1;
    private String mListDescription="";

    public MovieDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean getFavorite() {
        return mFavorite;
    }

    public void setmFavorite(boolean mFavorite) {
        this.mFavorite = mFavorite;
    }

    public long getDatabaseColumn() {
        return databaseColumn;
    }

    public void setDatabaseColumn(long databaseColumn) {
        this.databaseColumn = databaseColumn;
    }

    public String getmListDescription() {
        return mListDescription;
    }

    public void setmListDescription(String mListDescription) {
        this.mListDescription = mListDescription;
    }
}
