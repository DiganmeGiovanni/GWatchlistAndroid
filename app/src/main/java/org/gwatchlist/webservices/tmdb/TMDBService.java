package org.gwatchlist.webservices.tmdb;

import org.gwatchlist.webservices.tmdb.entities.TMDBMovieDetails;
import org.gwatchlist.webservices.tmdb.entities.TMDBSearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * Created by giovanni on 4/03/17.
 */
interface TMDBService {

    @GET("search/movie")
    Call<TMDBSearchResults> search(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{movieId}")
    Call<TMDBMovieDetails> movieDetails(@Path("movieId") long movieId, @Query("api_key") String apiKey,
                                        @Query("append_to_response") String appendToResponse);
}
