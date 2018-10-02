package MovieObjects;

public class MovieDetails {


    private int movieId =-1;
    private String moviePoster ="";
    private String movieTitle="";
    private String movieReleaseDate="";
    private String movieLength="";
    private String movieRating="";
    private String movieDescription="";
    private boolean mFavorite;
    private long databaseColumn=-1;
    private String mListDescription="";

    public MovieDetails() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public boolean ismFavorite() {
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
