package org.gwatchlist.data.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class GList {

    private long id;
    private String name;
    private Date createdAt;
    private List<String> sharedWith;
    private boolean isPersonalList;
    private String ownerEmail;
    private List<Movie> movies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<String> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public boolean isPersonalList() {
        return isPersonalList;
    }

    public void setPersonalList(boolean personalList) {
        isPersonalList = personalList;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
