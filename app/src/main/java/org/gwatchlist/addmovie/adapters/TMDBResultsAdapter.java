package org.gwatchlist.addmovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.gwatchlist.R;
import org.gwatchlist.webservices.Constants;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovie;

import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class TMDBResultsAdapter extends RecyclerView.Adapter<TMDBResultsAdapter.ViewHolder> {
    private OnMovieSelectedListener onMovieSelectedListener;

    private Context mContext;
    private List<TMDBMovie> movies;

    public TMDBResultsAdapter(Context mContext, List<TMDBMovie> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tmdb_search_result, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TMDBMovie movie = movies.get(position);

        Glide.with(mContext)
                .load(Constants.TMDB_IMAGE_SM + movie.getPosterPath())
                .crossFade()
                .into(holder.ivMoviePoster);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvSynopsis.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void replaceDataSet(List<TMDBMovie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);

        this.notifyDataSetChanged();
    }

    public void setOnMovieSelectedListener(OnMovieSelectedListener onMovieSelectedListener) {
        this.onMovieSelectedListener = onMovieSelectedListener;
    }

    public interface OnMovieSelectedListener {
        void onMovieSelected(TMDBMovie movie);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivMoviePoster;
        TextView tvTitle;
        TextView tvSynopsis;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ivMoviePoster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSynopsis = (TextView) itemView.findViewById(R.id.tv_synopsis);
        }

        @Override
        public void onClick(View view) {
            if (onMovieSelectedListener != null) {
                TMDBMovie tmdbMovie = movies.get(this.getLayoutPosition());
                onMovieSelectedListener.onMovieSelected(tmdbMovie);
            }
        }
    }
}
