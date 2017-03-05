package org.gwatchlist.webservices;

import org.gwatchlist.data.entities.GList;
import org.gwatchlist.data.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Created by giovanni on 30/01/17.
 */
interface GWatchlistService {

    @GET("user/login")
    Call<User> login(@Query("email") String email, @Query("name") String name);

    @GET("movies/list/personal")
    Call<GList> fetchPersonalList(@Query("owner_email") String ownerEmail);
}
