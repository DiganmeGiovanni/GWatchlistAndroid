package org.gwatchlist.data.dao;

import org.gwatchlist.data.entities.User;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 *
 * Created by giovanni on 1/03/17.
 */
public class UserDao {
    private Realm realm;

    public UserDao(Realm realm) {
        this.realm = realm;
    }

    public void save(User user) {
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

    public void deactivateAll() {
        realm.beginTransaction();
        RealmQuery<User> query = realm.where(User.class);
        RealmResults<User> users = query.findAll();
        for (User user : users) {
            user.setActive(false);
        }

        realm.commitTransaction();
    }

    public User findActive() {
        return realm
                .where(User.class)
                .equalTo("active", true)
                .findFirst();
    }
}
