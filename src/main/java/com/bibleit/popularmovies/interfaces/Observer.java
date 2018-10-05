package interfaces;

import objects.MovieDetails;

public interface Observer {

    public abstract void update(MovieDetails[] details);
}
