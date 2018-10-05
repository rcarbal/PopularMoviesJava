package interfaces;

import objects.MovieDetails;

public interface MovieDataObserver {

    public abstract void update(MovieDetails[] details);
}
