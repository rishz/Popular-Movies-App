package com.rishabhshukla.popularmoviesapp.model;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 30/03/17.
 */

public class MovieList{


    private ArrayList<SingleMovie> results;

    public ArrayList<SingleMovie> getResults() {
        return results;
    }

    public void setResults(ArrayList<SingleMovie> results) {
        this.results = results;
    }
}