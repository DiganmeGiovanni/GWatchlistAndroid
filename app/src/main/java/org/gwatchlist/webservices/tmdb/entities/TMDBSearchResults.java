package org.gwatchlist.webservices.tmdb.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * Created by giovanni on 4/03/17.
 */
public class TMDBSearchResults {

    private int page;
    private List<TMDBMovie> results;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("total_pages")
    private int totalPages;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TMDBMovie> getResults() {
        return results;
    }

    public void setResults(List<TMDBMovie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
