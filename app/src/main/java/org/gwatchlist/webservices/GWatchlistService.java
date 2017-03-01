package org.gwatchlist.webservices;

import org.gwatchlist.data.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by giovanni on 30/01/17.
 */
public interface GWatchlistService {

    @GET("user/login")
    Call<User> login(@Query("email") String email, @Query("name") String name);

}
