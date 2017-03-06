package org.gwatchlist.addmovie.detail;

import org.gwatchlist.BasePresenter;
import org.gwatchlist.BaseView;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovieDetails;

/**
 *
 * Created by giovanni on 5/03/17.
 */
public class AddMovieDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isLoading);

        void showMovieDetails(TMDBMovieDetails movieDetails);
    }

    interface Presenter extends BasePresenter {

        void fetchMovieDetails();
    }
}
