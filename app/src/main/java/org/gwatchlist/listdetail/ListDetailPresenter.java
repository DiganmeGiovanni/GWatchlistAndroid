package org.gwatchlist.listdetail;

import android.support.annotation.NonNull;
import android.util.Log;

import org.gwatchlist.data.dao.UserDao;
import org.gwatchlist.data.entities.GList;
import org.gwatchlist.data.entities.Movie;
import org.gwatchlist.data.entities.User;
import org.gwatchlist.util.GraphicUtils;
import org.gwatchlist.webservices.Calls;
import org.gwatchlist.webservices.WSCallback;

import io.realm.Realm;

/**
 *
 * Created by giovanni on 28/02/17.
 */
public class ListDetailPresenter implements ListDetailContract.Presenter {
    private ListDetailContract.View mView;
    private long listId;

    private Calls calls;
    private UserDao userDao;

    public ListDetailPresenter(@NonNull ListDetailContract.View mView, long listId) {
        this.mView = mView;
        this.listId = listId;

        this.calls = new Calls();
        this.userDao = new UserDao(Realm.getDefaultInstance());

        mView.setPresenter(this);
    }

    @Override
    public void addMovie() {

    }

    @Override
    public void fetchList() {
        mView.setLoadingIndicator(true);

        User user = userDao.findActive();
        if (listId == ListDetailActivity.LIST_PERSONAL) {
            calls.fetchPersonalList(user.getEmail(), new WSCallback<GList>() {

                @Override
                public void onResponse(boolean success, GList personalList) {
                    if (success) {
                        // TODO Persist list to local
                        // TODO Render list contents
                        Log.e(getClass().getName(), "Personal list successfully retrieved");
                        mView.setLoadingIndicator(false);

                    } else {

                        // TODO Show error to user
                    }
                }
            });
        } else {
            // TODO Fetch other list
        }
    }

    @Override
    public void loadMovies(boolean forceUpdate) {

    }

    @Override
    public void openMovie(@NonNull Movie movie) {

    }

    @Override
    public void toggleSeen(@NonNull Movie movie) {

    }

    @Override
    public void start() {
        // TODO Check for conenction available and fetch from local if no connection
        fetchList();
    }
}
