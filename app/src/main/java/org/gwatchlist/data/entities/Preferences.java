package org.gwatchlist.data.entities;

import io.realm.RealmObject;

/**
 *
 * Created by giovanni on 1/02/17.
 */
public class Preferences extends RealmObject {

    private boolean notifyOnMovieAdded;
    private boolean notifyOnListShared;
    private String theme;

    public boolean isNotifyOnMovieAdded() {
        return notifyOnMovieAdded;
    }

    public void setNotifyOnMovieAdded(boolean notifyOnMovieAdded) {
        this.notifyOnMovieAdded = notifyOnMovieAdded;
    }

    public boolean isNotifyOnListShared() {
        return notifyOnListShared;
    }

    public void setNotifyOnListShared(boolean notifyOnListShared) {
        this.notifyOnListShared = notifyOnListShared;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
