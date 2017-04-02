package com.rishabhshukla.popularmoviesapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhshukla.popularmoviesapp.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private String title, poster_path, backdrop_path, vote_average, is_video, is_adult, vote_count, release_date, popularity, original_language, overview;
    private int id;
    private CollapsingToolbarLayout toolbar_layout;

    private ImageView poster;
    private TextView movie_title, movie_rating, movie_release_date, movie_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poster = (ImageView) findViewById(R.id.poster);
        movie_title = (TextView) findViewById(R.id.title);
        movie_rating = (TextView) findViewById(R.id.rating);
        movie_release_date = (TextView) findViewById(R.id.release_date);
        movie_overview = (TextView) findViewById(R.id.overview);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        poster_path = i.getStringExtra("poster_path");
        backdrop_path = i.getStringExtra("backdrop_path");
        vote_average = i.getStringExtra("vote_average");
        is_adult = i.getStringExtra("is_adult");
        is_video = i.getStringExtra("is_video");
        vote_count = i.getStringExtra("vote_count");
        release_date = i.getStringExtra("release_date");
        popularity = i.getStringExtra("popularity");
        original_language = i.getStringExtra("original_language");
        overview = i.getStringExtra("overview");
        id = i.getIntExtra("id", 550);

        getSupportActionBar().setTitle(title);

        movie_title.setText(title);
        movie_rating.setText(vote_average);
        movie_release_date.setText(release_date);
        movie_overview.setText(overview);

        getSupportActionBar().setTitle(title);

        String posterImg = "http://image.tmdb.org/t/p/w500" + poster_path;
        Picasso.with(this)
                .load(posterImg)
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(poster);

//        ViewTarget view_target = new ViewTarget(toolbar_layout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
