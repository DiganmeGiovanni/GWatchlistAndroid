package org.gwatchlist;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 *
 * Created by giovanni on 21/01/17.
 */
public class GWatchlistApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
