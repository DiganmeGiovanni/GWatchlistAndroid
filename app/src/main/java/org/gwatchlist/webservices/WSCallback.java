package org.gwatchlist.webservices;

/**
 *
 * Created by giovanni on 1/02/17.
 */
public interface WSCallback<T> {

    void onResponse(boolean success, T body);
}
