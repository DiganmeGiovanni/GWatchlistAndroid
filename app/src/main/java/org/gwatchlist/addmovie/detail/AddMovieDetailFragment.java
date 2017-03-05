package org.gwatchlist.addmovie.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.gwatchlist.R;

/**
 *
 * Created by giovanni on 5/03/17.
 */
public class AddMovieDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_add_movie_details, container, false);
    }
}
