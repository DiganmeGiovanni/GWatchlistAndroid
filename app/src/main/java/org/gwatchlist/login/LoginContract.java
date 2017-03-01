package org.gwatchlist.login;

import org.gwatchlist.BasePresenter;
import org.gwatchlist.BaseView;
import org.gwatchlist.data.entities.User;

/**
 * This specifies the contract between the view and the presenter
 * Created by giovanni on 1/02/17.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void attemptGoogleLogin();

        void showFailedLogin();

        void showPersonalList(User user);
    }

    interface Presenter extends BasePresenter {

        void attemptLogin();

        void attemptLogin(String email, String name);

        void showFailedLogin();
    }
}
