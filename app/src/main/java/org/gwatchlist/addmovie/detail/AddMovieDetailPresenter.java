package org.gwatchlist.addmovie.detail;

import org.gwatchlist.webservices.WSCallback;
import org.gwatchlist.webservices.tmdb.ApiTMDB;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovieDetails;

/**
 *
 * Created by giovanni on 5/03/17.
 */
class AddMovieDetailPresenter implements AddMovieDetailContract.Presenter {

    private AddMovieDetailContract.View mView;
    private long listId;
    private long movieId;

    private ApiTMDB apiTMDB;

    AddMovieDetailPresenter(AddMovieDetailContract.View mView, long listId, long movieId) {
        this.mView = mView;
        this.listId = listId;
        this.movieId = movieId;

        this.apiTMDB = new ApiTMDB();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void fetchMovieDetails() {
        mView.setLoadingIndicator(true);

        apiTMDB.movieDetails(movieId, new WSCallback<TMDBMovieDetails>() {
            @Override
            public void onResponse(boolean success, TMDBMovieDetails movieDetails) {
                if (success) {
                    mView.showMovieDetails(movieDetails);
                } else {
                    // TODO Show error
                }

                mView.setLoadingIndicator(false);
            }
        });
    }
}
