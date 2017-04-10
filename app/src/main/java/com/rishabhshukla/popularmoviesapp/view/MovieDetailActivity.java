package com.rishabhshukla.popularmoviesapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.controller.ReviewAdapter;
import com.rishabhshukla.popularmoviesapp.controller.VideoAdapter;
import com.rishabhshukla.popularmoviesapp.model.ReviewList;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;
import com.rishabhshukla.popularmoviesapp.model.SingleReview;
import com.rishabhshukla.popularmoviesapp.model.SingleVideo;
import com.rishabhshukla.popularmoviesapp.model.VideoList;
import com.rishabhshukla.popularmoviesapp.rest.TheMoviesDBApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private String API_KEY = "c49a0ac24db60b4efb6b0d46f212a7f2";
    private String title, poster_path, backdrop_path, vote_average, is_video, is_adult, vote_count, release_date, popularity, original_language, overview;
    private int id;
    private List<SingleReview> reviews;
    private List<SingleVideo> videos;
    private CollapsingToolbarLayout toolbar_layout;
    TheMoviesDBApi theMovieDbApi;
    private CardView reviews_card, videos_card;
    RecyclerView rvVideo,rvReview;
    private ImageView poster, backdrop;
    SingleMovie movie;
    private TextView movie_title, movie_rating, movie_release_date, movie_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videos_card = (CardView) findViewById(R.id.videos_card);
        reviews_card = (CardView) findViewById(R.id.reviews_card);
        poster = (ImageView) findViewById(R.id.poster);
        backdrop = (ImageView) findViewById(R.id.posterpath);
        movie_title = (TextView) findViewById(R.id.title);
        movie_rating = (TextView) findViewById(R.id.rating);
        movie_release_date = (TextView) findViewById(R.id.release_date);
        movie_overview = (TextView) findViewById(R.id.overview);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        rvReview = (RecyclerView) findViewById(R.id.rv_review);
        rvVideo = (RecyclerView) findViewById(R.id.rv_video);

        theMovieDbApi = new TheMoviesDBApi();

        Intent i = getIntent();
        movie = (SingleMovie) i.getSerializableExtra("movie");
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

        String posterImg = "http://image.tmdb.org/t/p/w185" + poster_path;
        Picasso.with(this)
                .load(posterImg)
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(poster);

        String backdropImg = "http://image.tmdb.org/t/p/w500" + backdrop_path;
        Picasso.with(this)
                .load(backdropImg)
                .fit()
                .into(backdrop);

//            toolbar_layout.setBackground(backdrop.getDrawable());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Noodle noodle = Noodle.with(MovieDetailActivity.this).build();
//                if(movie!=null){
//                    noodle.put("fav",movie);
//                }
//                Snackbar.make(view, "Added to Favourites", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                SharedPreferences prefs = getSharedPreferences("movies",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
            }
        });

        reviews = Collections.emptyList();

        callReviews();
        callVideos();
    }

//    public Drawable drawableFromUrl(String url) throws IOException {
//        Bitmap x;
//
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.connect();
//        InputStream input = connection.getInputStream();
//
//        x = BitmapFactory.decodeStream(input);
//        return new BitmapDrawable(x);
//    }
private void callReviews(){
    Call<ReviewList> call = theMovieDbApi.getMovieClient().searchMovieReviews(id, API_KEY);
    if(call!=null){
        call.enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(Call<ReviewList> call, Response<ReviewList> response) {
                reviews = new ArrayList<SingleReview>();
                reviews = response.body().getResults();
//                for(Review singleReview : reviews){
//                    Log.e("Review", singleReview.getContent());
////                        reviews.add(singleReview);
//                }
                if(reviews.size()>0){
                    reviews_card.setVisibility(View.VISIBLE);
                    rvReview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ReviewAdapter adapter = new ReviewAdapter(reviews, getApplicationContext());
                    rvReview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {

            }
        });
    }
}

    private void callVideos(){
        Call<VideoList> call = theMovieDbApi.getMovieClient().searchMovieVideos(id, API_KEY);
        if(call!=null){
            call.enqueue(new Callback<VideoList>() {
                @Override
                public void onResponse(Call<VideoList> call, Response<VideoList> response) {
                    videos = new ArrayList<SingleVideo>();
                    videos = response.body().getResults();
//                    for(SingleVideo singleVideo : videos){
//                        Log.e("Video", singleVideo.getKey());
////                        reviews.add(singleReview);
//                    }
                    if(videos.size()>0){
                        videos_card.setVisibility(View.VISIBLE);
                        rvVideo.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        VideoAdapter adapter = new VideoAdapter(videos, getApplicationContext());
                        Log.e("Size", " " + adapter.getItemCount());
                        rvVideo.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<VideoList> call, Throwable t) {

                }
            });
        }
    }

}
