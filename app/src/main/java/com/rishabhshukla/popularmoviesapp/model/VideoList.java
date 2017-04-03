package com.rishabhshukla.popularmoviesapp.model;

import java.util.List;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class VideoList {


    private int id;
    private List<SingleVideo> results;

    public void setId(int id) {
        this.id = id;
    }

    public void setResults(List<SingleVideo> results) {
        this.results = results;
    }

    public int getId() {

        return id;
    }

    public List<SingleVideo> getResults() {
        return results;
    }
}
