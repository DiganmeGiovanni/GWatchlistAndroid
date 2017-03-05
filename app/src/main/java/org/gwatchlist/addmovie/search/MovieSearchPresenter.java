package org.gwatchlist.addmovie.search;

import android.support.annotation.NonNull;

import org.gwatchlist.webservices.WSCallback;
import org.gwatchlist.webservices.tmdb.ApiTMDB;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovie;

import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
class MovieSearchPresenter implements MovieSearchContract.Presenter {
    private MovieSearchContract.View mView;
    private ApiTMDB apiTMDB;

    public MovieSearchPresenter(@NonNull MovieSearchContract.View mView) {
        this.mView = mView;
        this.apiTMDB = new ApiTMDB();

        this.mView.setPresenter(this);
    }

    @Override
    public void searchTmdb(String query) {
        mView.setSearchingIndicator(true);
        apiTMDB.searchMovie(query, new WSCallback<List<TMDBMovie>>() {

            @Override
            public void onResponse(boolean success, List<TMDBMovie> movies) {
                if (success) {
                    mView.showSearchResults(movies);
                }

                mView.setSearchingIndicator(false);
            }
        });
    }

    @Override
    public void start() {

    }
}
