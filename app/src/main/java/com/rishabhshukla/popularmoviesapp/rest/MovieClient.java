package com.rishabhshukla.popularmoviesapp.rest;

import com.rishabhshukla.popularmoviesapp.model.MovieList;
import com.rishabhshukla.popularmoviesapp.model.ReviewList;
import com.rishabhshukla.popularmoviesapp.model.VideoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rishabhshukla on 30/03/17.
 */

public interface MovieClient {
//
//    @GET("movie/popular?api_key=c49a0ac24db60b4efb6b0d46f212a7f2")
//    Call<MovieList> getMovieList();
//
//    @GET("movie/popular?api_key=c49a0ac24db60b4efb6b0d46f212a7f2")
//    Call<MovieList> getMovieList(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie?sort_by=vote_count.desc")
    Call<MovieList> getMostRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieList> getMostPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("discover/movie?sort_by=vote_count.desc")
    Call<MovieList> getMostRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Call<MovieList> getMostPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    //Search Movie By ID

    @GET("movie/{id}")
    Call<MovieList> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    //Search Movie By Name

    @GET("search/movie?query=")
    Call<MovieList> searchMovieDetails(String name, @Query("api_key") String apiKey);

    //Search Movie Videos

    @GET("movie/{id}/videos")
    Call<VideoList> searchMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    //Search Movie Reviews

    @GET("movie/{id}/reviews")
    Call<ReviewList> searchMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

}
