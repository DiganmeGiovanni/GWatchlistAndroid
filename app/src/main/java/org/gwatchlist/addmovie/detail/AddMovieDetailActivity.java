package org.gwatchlist.addmovie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.gwatchlist.R;
import org.gwatchlist.util.ActivityUtils;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class AddMovieDetailActivity extends AppCompatActivity {
    public static final String TMDB_ID = "TMDB_ID";
    public static final String TARGET_LIST_ID = "LIST_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_movie_detail);

        //
        // Reuse previously added fragment
        AddMovieDetailFragment addMovieDetailFragment = (AddMovieDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (addMovieDetailFragment == null) {
            addMovieDetailFragment = new AddMovieDetailFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    addMovieDetailFragment,
                    R.id.contentFrame
            );
        }

        // Retrieve arguments
        long listId = getIntent().getLongExtra(TARGET_LIST_ID, 0);
        long tmdbId = getIntent().getLongExtra(TMDB_ID, 0);

        // Initialize presenter
        new AddMovieDetailPresenter(addMovieDetailFragment, listId, tmdbId);
    }
}
