package org.gwatchlist.login;

import android.support.annotation.NonNull;

import org.gwatchlist.webservices.Calls;
import org.gwatchlist.webservices.WSCallback;
import org.gwatchlist.data.entities.User;

/**
 *
 * Created by giovanni on 1/02/17.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;
    private Calls calls;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);

        calls = new Calls();
    }

    @Override
    public void attemptLogin() {
        mLoginView.attemptGoogleLogin();
    }

    @Override
    public void attemptLogin(String email, String name) {
        calls.login(email, name, new WSCallback<User>() {

            @Override
            public void onResponse(boolean success, User user) {
                if (success) {
                    mLoginView.showPersonalList(user);
                } else {
                    mLoginView.showFailedLogin();
                }
            }
        });
    }

    @Override
    public void showFailedLogin() {
        mLoginView.showFailedLogin();
    }

    @Override
    public void start() {

    }
}
