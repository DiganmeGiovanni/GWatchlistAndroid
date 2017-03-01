package org.gwatchlist.webservices;

import android.util.Log;

import org.gwatchlist.data.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *
 * Created by giovanni on 30/01/17.
 */
public class Calls {

    private GWatchlistService watchlistService;

    public Calls() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.apiBasePath)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        watchlistService = retrofit.create(GWatchlistService.class);
    }

    public void login(String email, String name, final WSCallback<User> callback) {
        Call<User> call = watchlistService.login(email, name);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d(getClass().getName(), "Successful remote login");

                    callback.onResponse(true, response.body());
                } else {
                    Log.w(getClass().getName(), "User could not be logged in on remote backend");

                    callback.onResponse(false, null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(getClass().getName(), "Could not logged in to user");
                Log.e(getClass().getName(), "Says: " + t.getMessage());

                callback.onResponse(false, null);
            }
        });
    }
}
