package org.gwatchlist.listdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.gwatchlist.R;
import org.gwatchlist.addmovie.search.MovieSearchActivity;
import org.gwatchlist.data.entities.Movie;

import java.util.List;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailFragment extends Fragment implements ListDetailContract.View {

    private ListDetailContract.Presenter mPresenter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_list_detail, container, false);

        setupAddMovieButton();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        ProgressBar pbLoadingIndicator = (ProgressBar) rootView.findViewById(R.id.pb_loading_indicator);
        RecyclerView rvListMovies = (RecyclerView) rootView.findViewById(R.id.rv_list_movies);

        if (isLoading) {
            rvListMovies.setVisibility(View.GONE);
            pbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            pbLoadingIndicator.setVisibility(View.GONE);
            rvListMovies.setVisibility(View.VISIBLE);
        }
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
        this.mPresenter = presenter;
    }

    /**
     * Prepares the add movie button to display Add movie UI
     * on action
     */
    private void setupAddMovieButton() {
        FloatingActionButton btnAddMovie = (FloatingActionButton) rootView
                .findViewById(R.id.fab_add_movie);
        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MovieSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
