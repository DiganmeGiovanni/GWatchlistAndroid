package org.gwatchlist.listdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gwatchlist.R;
import org.gwatchlist.data.entities.Movie;

import java.util.List;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailFragment extends Fragment implements ListDetailContract.View {

    private ListDetailContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_list_detail, container, false);

        return rootView;
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void showAddMovie() {

    }

    @Override
    public void showMovieDetailsUI(long movieId) {

    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void showNoMovies() {

    }

    @Override
    public void setPresenter(ListDetailContract.Presenter presenter) {

    }
}
