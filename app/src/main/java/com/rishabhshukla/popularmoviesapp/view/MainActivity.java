package com.rishabhshukla.popularmoviesapp.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.controller.MovieAdapter;
import com.rishabhshukla.popularmoviesapp.model.MovieList;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;
import com.rishabhshukla.popularmoviesapp.rest.TheMoviesDBApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ArrayList<SingleMovie> singleMovieArrayList;
    MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        singleMovieArrayList = new ArrayList<>();
        movieAdapter = new MovieAdapter(singleMovieArrayList);

        TheMoviesDBApi theMovieDbApi = new TheMoviesDBApi();
        if(isOnline()) {
            theMovieDbApi.getMovieClient().getMovieList().enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    for (SingleMovie singleMovie : response.body().getResults()) {
                        Log.e("Movie", singleMovie.getOriginalTitle());
                        singleMovieArrayList.add(singleMovie);
                    }
                    movieAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {

                }

            });
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movieRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
