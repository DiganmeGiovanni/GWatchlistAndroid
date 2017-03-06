package org.gwatchlist.addmovie.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.gwatchlist.R;
import org.gwatchlist.util.ActivityUtils;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class MovieSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_movie_search);

        // Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        // Reuse previous fragment
        MovieSearchFragment movieSearchFragment = (MovieSearchFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (movieSearchFragment == null) {
            movieSearchFragment = new MovieSearchFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    movieSearchFragment,
                    R.id.contentFrame
            );
        }

        // Initialize presenter
        new MovieSearchPresenter(movieSearchFragment);
    }
}
