package org.gwatchlist.listdetail;

import android.support.annotation.NonNull;

import org.gwatchlist.BasePresenter;
import org.gwatchlist.BaseView;
import org.gwatchlist.data.entities.Movie;

import java.util.List;

/**
 *
 * Created by giovanni on 1/02/17.
 */
class ListDetailContract {

    interface Presenter extends BasePresenter {

        void addMovie();

        void fetchList(long listId);

        void fetchPersonalList();

        void loadMovies(boolean forceUpdate);

        void openMovie(@NonNull Movie movie);

        void toggleSeen(@NonNull Movie movie);
    }

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isLoading);

        void showAddMovie();

        void showMovieDetailsUI(long movieId);

        void showMovies(List<Movie> movies);

        void showNoMovies();

    }
}
