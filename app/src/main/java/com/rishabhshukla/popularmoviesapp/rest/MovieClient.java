package com.rishabhshukla.popularmoviesapp.rest;

import com.rishabhshukla.popularmoviesapp.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rishabhshukla on 30/03/17.
 */

public interface MovieClient {

    @GET("movie/popular?api_key=c49a0ac24db60b4efb6b0d46f212a7f2")
    Call<MovieList> getMovieList();

    @GET("movie/popular?api_key=c49a0ac24db60b4efb6b0d46f212a7f2")
    Call<MovieList> getMovieList(@Query("page") int page);


}
