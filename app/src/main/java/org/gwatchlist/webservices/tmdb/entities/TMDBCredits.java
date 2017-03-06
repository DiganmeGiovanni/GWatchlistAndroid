package org.gwatchlist.webservices.tmdb.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 *
 * Created by giovanni on 5/03/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBCredits {

    private List<TMDBCrew> crew;

    public List<TMDBCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<TMDBCrew> crew) {
        this.crew = crew;
    }
}
