package org.gwatchlist.webservices.tmdb;

import android.util.Log;

import org.gwatchlist.webservices.Constants;
import org.gwatchlist.webservices.WSCallback;
import org.gwatchlist.webservices.tmdb.entities.TMDBMovie;
import org.gwatchlist.webservices.tmdb.entities.TMDBSearchResults;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class ApiTMDB {
    private TMDBService tmdbService;
    private String apiKey;

    public ApiTMDB() {
        //apiKey = Resources.getSystem().getString(R.string.tmdb_api_key);
        apiKey = "b05e87f356ef223c5aeacf0bcae54d04";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TMDB_BASE_PATH)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        tmdbService = retrofit.create(TMDBService.class);
    }

    public void searchMovie(String query, final WSCallback<List<TMDBMovie>> callback) {
        Call<TMDBSearchResults> call = tmdbService.search(apiKey, query);
        call.enqueue(new Callback<TMDBSearchResults>() {
            @Override
            public void onResponse(Call<TMDBSearchResults> call, Response<TMDBSearchResults> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(true, response.body().getResults());
                } else {
                    Log.w(getClass().getName(), "TMDB Search failed");
                    callback.onResponse(false, null);
                }
            }

            @Override
            public void onFailure(Call<TMDBSearchResults> call, Throwable t) {
                Log.w(getClass().getName(), "TMDB Search failed");
                Log.e(getClass().getName(), "Message: " + t.getMessage());

                callback.onResponse(false, null);
            }
        });
    }
}
