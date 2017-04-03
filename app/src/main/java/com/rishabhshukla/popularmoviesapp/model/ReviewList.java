package com.rishabhshukla.popularmoviesapp.model;

import java.util.List;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class ReviewList {
    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void setResults(List<SingleReview> results) {
        this.results = results;
    }

    public int getId() {

        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public List<SingleReview> getResults() {
        return results;
    }

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    private List<SingleReview> results;
}
