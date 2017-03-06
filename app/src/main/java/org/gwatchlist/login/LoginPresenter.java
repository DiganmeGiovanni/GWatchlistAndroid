package org.gwatchlist.login;

import android.support.annotation.NonNull;

import org.gwatchlist.data.dao.UserDao;
import org.gwatchlist.webservices.Calls;
import org.gwatchlist.webservices.WSCallback;
import org.gwatchlist.data.entities.User;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 *
 * Created by giovanni on 1/02/17.
 */
class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;
    private Calls calls;

    private UserDao userDao;

    LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);

        calls = new Calls();
        userDao = new UserDao(Realm.getDefaultInstance());
    }

    @Override
    public void start() {

    }

    @Override
    public void attemptAutoLogin() {
        User user = userDao.findActive();
        if (user != null) {

            // Go to personal list
            mLoginView.showPersonalList(user);
        } else {
            mLoginView.setLoadingIndicator(false);
        }
    }

    @Override
    public void attemptLogin(String email, String name) {
        mLoginView.setLoadingIndicator(true);
        calls.login(email, name, new WSCallback<User>() {

            @Override
            public void onResponse(boolean success, User user) {
                if (success) {

                    // Persist active user to database
                    userDao.deactivateAll();
                    user.setActive(true);
                    user = userDao.create(user);

                    // Go to personal list
                    mLoginView.showPersonalList(user);
                } else {
                    mLoginView.showFailedLogin();
                    mLoginView.setLoadingIndicator(false);
                }
            }
        });
    }
}
