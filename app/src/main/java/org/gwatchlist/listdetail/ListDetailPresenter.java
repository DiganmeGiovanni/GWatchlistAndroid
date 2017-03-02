package org.gwatchlist.listdetail;

import android.support.annotation.NonNull;

import org.gwatchlist.data.entities.Movie;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailPresenter implements ListDetailContract.Presenter {
    private ListDetailContract.View mListDetailView;

    public ListDetailPresenter(@NonNull ListDetailContract.View mListDetailView) {
        this.mListDetailView = mListDetailView;
    }

    @Override
    public void addMovie() {

    }

    @Override
    public void fetchList(long listId) {

    }

    @Override
    public void fetchPersonalList() {

    }

    @Override
    public void loadMovies(boolean forceUpdate) {

    }

    @Override
    public void openMovie(@NonNull Movie movie) {

    }

    @Override
    public void toggleSeen(@NonNull Movie movie) {

    }

    @Override
    public void start() {

    }
}
