package com.rishabhshukla.popularmoviesapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.rishabhshukla.popularmoviesapp.model.ReviewList;
import com.rishabhshukla.popularmoviesapp.model.SingleReview;
import com.rishabhshukla.popularmoviesapp.model.SingleVideo;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        reviews = Collections.emptyList();

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
                    reviews_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ReviewsAdapter adapter = new ReviewsAdapter(reviews, R.layout.reviews_layout, getApplicationContext());
                    Log.e("Size", " " + adapter.getItemCount());
                    reviews_rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {

            }
        });
    }
}

}
