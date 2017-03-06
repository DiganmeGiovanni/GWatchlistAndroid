package org.gwatchlist.addmovie.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.gwatchlist.R;
import org.gwatchlist.addmovie.adapters.TMDBResultsAdapter;
import org.gwatchlist.addmovie.detail.AddMovieDetailActivity;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovie;

import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class MovieSearchFragment extends Fragment implements MovieSearchContract.View {

    private MovieSearchContract.Presenter mPresenter;
    private View rootView;
    private ProgressBar pbSearching;
    private RecyclerView rvSearchResults;
    private TMDBResultsAdapter resultsAdapter;

    private DelayedTMDBSearch delayedTMDBSearch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        delayedTMDBSearch = new DelayedTMDBSearch();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_add_movie_search, container, false);

        configureSearchInput();
        return rootView;
    }

    @Override
    public void setPresenter(MovieSearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setSearchingIndicator(boolean isSearching) {
        if (pbSearching == null) {
            pbSearching = (ProgressBar) rootView.findViewById(R.id.pb_searching);
            rvSearchResults = (RecyclerView) rootView.findViewById(R.id.rv_search_results);
        }

        if (isSearching) {
            rvSearchResults.setVisibility(View.GONE);
            pbSearching.setVisibility(View.VISIBLE);
        } else {
            pbSearching.setVisibility(View.GONE);
            rvSearchResults.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMovieDetails(TMDBMovie movie) {

    }

    @Override
    public void showSearchResults(List<TMDBMovie> movies) {
        Log.d(getClass().getName(), "Refreshing results");

        if (resultsAdapter == null) {
            resultsAdapter = new TMDBResultsAdapter(getContext(), movies);
            resultsAdapter.setOnMovieSelectedListener(new TMDBResultsAdapter.OnMovieSelectedListener() {
                @Override
                public void onMovieSelected(TMDBMovie movie) {
                    Intent intent = new Intent(getContext(), AddMovieDetailActivity.class);
                    intent.putExtra(AddMovieDetailActivity.TMDB_ID, movie.getId());


                    startActivity(intent);
                }
            });

            rvSearchResults = (RecyclerView) rootView
                    .findViewById(R.id.rv_search_results);
            rvSearchResults.setHasFixedSize(true);
            rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
            rvSearchResults.setAdapter(resultsAdapter);
        } else {
            resultsAdapter.replaceDataSet(movies);
        }
    }

    private void configureSearchInput() {
        EditText etMovieTitle = (EditText) rootView.findViewById(R.id.et_movie_title);
        etMovieTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int bofore, int count) {
                if (charSequence.length() > 1) {
                    delayedTMDBSearch.postDelayedSearch(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    private class DelayedTMDBSearch {
        private final long DELAY = 600;
        private Handler handler = new Handler();
        private boolean isFirstSearch = true;

        void postDelayedSearch(final String query) {
            handler.removeCallbacksAndMessages(null); // Abort previous posted searches
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenter.searchTmdb(query);
                }
            }, isFirstSearch ? 0 : DELAY);

            isFirstSearch = false;
        }
    }
}
