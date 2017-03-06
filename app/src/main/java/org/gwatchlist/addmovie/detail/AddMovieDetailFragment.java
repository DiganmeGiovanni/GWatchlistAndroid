package org.gwatchlist.addmovie.detail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.gwatchlist.R;
import org.gwatchlist.databinding.FragAddMovieDetailsBinding;
import org.gwatchlist.util.TextUtils;
import org.gwatchlist.webservices.Constants;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovieDetails;

/**
 *
 * Created by giovanni on 5/03/17.
 */
public class AddMovieDetailFragment extends Fragment implements AddMovieDetailContract.View {

    private AddMovieDetailContract.Presenter mPresenter;
    private FragAddMovieDetailsBinding viewBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_add_movie_details, container, false);
        viewBinding = FragAddMovieDetailsBinding.bind(rootView);

        mPresenter.fetchMovieDetails();
        return rootView;
    }

    @Override
    public void setPresenter(AddMovieDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            viewBinding.llDetailsContainer.setVisibility(View.GONE);
            viewBinding.pbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            viewBinding.pbLoadingIndicator.setVisibility(View.GONE);
            viewBinding.llDetailsContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMovieDetails(TMDBMovieDetails movieDetails) {
        viewBinding.tvReleaseDate.setText(TextUtils.formatRelaseDate(movieDetails.getReleaseDate()));
        viewBinding.tvRuntime.setText(movieDetails.getRuntime() + " " + getString(R.string.mins));
        viewBinding.tvVoteAverage.setText(String.format("%s", movieDetails.getVoteAverage()));
        viewBinding.tvSynopsis.setText(movieDetails.getOverview());

        if (movieDetails.getTagLine() == null || movieDetails.getTagLine().length() == 0) {
            viewBinding.tvTagLine.setVisibility(View.GONE);
        } else {
            viewBinding.tvTagLine.setText(movieDetails.getTagLine());
        }

        //
        // Show movie's title into toolbar
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity()
                .findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(movieDetails.getTitle());

        //
        // Show movie image into toolbar
        ImageView ivPoster = (ImageView) getActivity().findViewById(R.id.image);
        if (ivPoster != null) {
            Glide.with(getContext())
                    .load(Constants.TMDB_IMAGE_MD + movieDetails.getPosterPath())
                    .asBitmap()
                    .into(new BitmapImageViewTarget(ivPoster) {

                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);

                            //
                            // Extract predominant color and use it as background
                            new Palette.Builder(resource).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                                    if (darkVibrantSwatch != null) {
                                        collapsingToolbarLayout.setBackgroundColor(darkVibrantSwatch.getRgb());
                                    } else {
                                        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                                        if (lightMutedSwatch != null) {
                                            collapsingToolbarLayout
                                                    .setBackgroundColor(lightMutedSwatch.getRgb());
                                        } else {
                                            collapsingToolbarLayout.setBackgroundColor(Color.RED);
                                        }
                                    }
                                }
                            });
                        }
                    });
        }
    }
}
