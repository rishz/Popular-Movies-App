package com.rishabhshukla.popularmoviesapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.controller.EndlessScrollListener;
import com.rishabhshukla.popularmoviesapp.controller.MovieAdapter;
import com.rishabhshukla.popularmoviesapp.model.MovieList;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;
import com.rishabhshukla.popularmoviesapp.rest.TheMoviesDBApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private String API_KEY = "c49a0ac24db60b4efb6b0d46f212a7f2";
    ArrayList<SingleMovie> singleMovieArrayList;
    MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeContainer;
    private EndlessScrollListener scrollListener;
    int pageNumber=2;
    TheMoviesDBApi theMovieDbApi;
    private int filter = 0;
    RecyclerView.LayoutManager layoutManager;
    private CharSequence items[] = {"Highest Rated", "Most Popular", "Most Rated"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        singleMovieArrayList = new ArrayList<>();
        movieAdapter = new MovieAdapter(singleMovieArrayList);

        theMovieDbApi = new TheMoviesDBApi();

        if(isOnline()) {
                theMovieDbApi.getMovieClient().getMostPopularMovies(API_KEY).enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        for (SingleMovie singleMovie : response.body().getResults()) {
                            Log.e("Movie", singleMovie.getOriginalTitle());
                            singleMovieArrayList.add(singleMovie);
                        }
//                        movieAdapter.addAll(singleMovieArrayList);
                        movieAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }
                });

        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movieRecyclerView);
        layoutManager = new GridLayoutManager(this,2);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
             layoutManager = new GridLayoutManager(this,2);
        }
        else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
             layoutManager = new GridLayoutManager(this,3);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
        scrollListener = new EndlessScrollListener((GridLayoutManager) layoutManager) {
            @Override
            public int getFooterViewType(int defaultNoFooterViewType) {
                return -1;
            }

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }

        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);



        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.

                Call<MovieList> call = null;

                switch (filter){
                    case 0:{
                        call = theMovieDbApi.getMovieClient().getMostPopularMovies(API_KEY);
                        break;
                    }
                    case 1:{
                        call = theMovieDbApi.getMovieClient().getTopRatedMovies(API_KEY);
                        break;
                    }
                    case 2:{
                        call = theMovieDbApi.getMovieClient().getMostRatedMovies(API_KEY);
                        break;
                    }
                }

                if(isOnline()) {
                    if(call!=null){
                        movieAdapter.clear();
                        call.enqueue(new Callback<MovieList>() {
                            @Override
                            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                for (SingleMovie singleMovie : response.body().getResults()) {
                                    Log.e("Movie", singleMovie.getOriginalTitle());
                                    singleMovieArrayList.add(singleMovie);
                                }
                                movieAdapter.addAll(singleMovieArrayList);
                                movieAdapter.notifyDataSetChanged();


                                swipeContainer.setRefreshing(false);

                            }

                            @Override
                            public void onFailure(Call<MovieList> call, Throwable t) {

                            }

                        });
                    }
                }else{
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    swipeContainer.setRefreshing(false);
                }

            }
        });


        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void loadNextDataFromApi(int page) {
        Call<MovieList> call = null;

        switch (filter){
            case 0:{
                call = theMovieDbApi.getMovieClient().getMostPopularMovies(API_KEY,pageNumber);
                break;
            }
            case 1:{
                call = theMovieDbApi.getMovieClient().getTopRatedMovies(API_KEY,pageNumber);
                break;
            }
            case 2:{
                call = theMovieDbApi.getMovieClient().getMostRatedMovies(API_KEY,pageNumber);
                break;
            }
        }

        if(isOnline()) {
            if(call!=null) {
//                movieAdapter.clear();
                call.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        for (SingleMovie singleMovie : response.body().getResults()) {
                            Log.e("Movie", singleMovie.getOriginalTitle());
                            singleMovieArrayList.add(singleMovie);
                        }
//                        movieAdapter.addAll(singleMovieArrayList);
                        movieAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }


                });
            }
            pageNumber++;
        }else{
            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_search:{

                break;
            }
            case R.id.action_sort:{
                Log.e("CHECK CLICK", "CLICKED! YO!");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sort By:").setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:{
                                    filter = 0;
                                    callMovies();
                                break;
                            }
                            case 1:{
                                    filter = 1;
                                    callMovies();
                                break;
                            }
                            case 2:{
                                    filter = 2;
                                    callMovies();
                                break;
                            }
                        }
                    }
                });
                builder.show();
                break;
            }
            case R.id.action_settings:{

                break;
            }
            case R.id.menu_fav:{
                Intent i = new Intent(this,FavouritesActivity.class);
                startActivity(i);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void callMovies() {

        Call<MovieList> call = null;

        switch (filter){
            case 0:{
                call = theMovieDbApi.getMovieClient().getMostPopularMovies(API_KEY);
                break;
            }
            case 1:{
                call = theMovieDbApi.getMovieClient().getTopRatedMovies(API_KEY);
                break;
            }
            case 2:{
                call = theMovieDbApi.getMovieClient().getMostRatedMovies(API_KEY);
                break;
            }
        }

        if(isOnline()) {
            if(call!=null) {
                movieAdapter.clear();
                call.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        for (SingleMovie singleMovie : response.body().getResults()) {
                            Log.e("Movie", singleMovie.getOriginalTitle());
                            singleMovieArrayList.add(singleMovie);
                        }
                        movieAdapter.addAll(singleMovieArrayList);
                        movieAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }


                });
            }
        }else{
            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        scrollListener = new EndlessScrollListener((GridLayoutManager) layoutManager) {
            @Override
            public int getFooterViewType(int defaultNoFooterViewType) {
                return -1;
            }

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }

        };
    }
}
