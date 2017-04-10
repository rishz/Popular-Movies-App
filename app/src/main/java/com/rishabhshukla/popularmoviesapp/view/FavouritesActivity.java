package com.rishabhshukla.popularmoviesapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.noodle.Noodle;
import com.noodle.Result;
import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Noodle noodle = Noodle.with(this).build();

        Result<SingleMovie> movies = noodle.get("movie", SingleMovie.class);
    }
}
