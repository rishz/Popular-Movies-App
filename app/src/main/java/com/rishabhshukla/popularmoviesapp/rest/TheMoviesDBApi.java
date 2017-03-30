package com.rishabhshukla.popularmoviesapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rishabhshukla on 30/03/17.
 */
public class TheMoviesDBApi {

    public MovieClient getMovieClient() {
        String BASE_URL = "https://api.themoviedb.org/3/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // parcel we want to use.. Gson here
                .build();
        return retrofit.create(MovieClient.class);
    }
}
