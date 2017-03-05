package org.gwatchlist.addmovie.search;

import org.gwatchlist.BasePresenter;
import org.gwatchlist.BaseView;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovie;

import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class MovieSearchContract {

    interface View extends BaseView<Presenter> {

        void showMovieDetails(TMDBMovie movie);

        void showSearchResults(List<TMDBMovie> movies);

        void setSearchingIndicator(boolean isSearching);
    }

    interface Presenter extends BasePresenter {

        void searchTmdb(String query);
    }
}
