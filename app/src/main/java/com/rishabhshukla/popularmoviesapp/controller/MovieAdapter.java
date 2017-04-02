package com.rishabhshukla.popularmoviesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;
import com.rishabhshukla.popularmoviesapp.view.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabhshukla on 30/03/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private ArrayList<SingleMovie> movies;
    private Context context;
    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public MovieAdapter(ArrayList<SingleMovie> movieArrayList){
        movies = movieArrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie,null));
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(movies.get(position).getOriginalTitle());
        String poster = "http://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(poster)
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.imageView);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if(isLongClick){
                    holder.DetailIntent(movies.get(position).getTitle(), movies.get(position).getPosterPath(), String.valueOf(movies.get(position).getAdult().toString()), movies.get(position).getBackdropPath(), movies.get(position).getOriginalLanguage(), String.valueOf(movies.get(position).getPopularity()), movies.get(position).getReleaseDate(), String.valueOf(movies.get(position).getVoteCount()), String.valueOf(movies.get(position).getVoteAvg()), String.valueOf(movies.get(position).getVideo()), movies.get(position).getOverview(), movies.get(position).getId());
                }
                else {
                    holder.DetailIntent(movies.get(position).getTitle(), movies.get(position).getPosterPath(), String.valueOf(movies.get(position).getAdult()), movies.get(position).getBackdropPath(), movies.get(position).getOriginalLanguage(), String.valueOf(movies.get(position).getPopularity()), movies.get(position).getReleaseDate(), String.valueOf(movies.get(position).getVoteCount()), String.valueOf(movies.get(position).getVoteAvg()), String.valueOf(movies.get(position).getVideo()), movies.get(position).getOverview(), movies.get(position).getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textView;
        ImageView imageView;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.movie_name);
            imageView = (ImageView) itemView.findViewById(R.id.movie_image);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setClickListener(ItemClickListener clickListener){
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(itemView, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(itemView, getAdapterPosition(), true);
            return false;
        }

        public void DetailIntent(String title, String poster_path, String is_adult, String backdrop_path,
                                 String original_language, String popularity, String release_date, String vote_count,
                                 String vote_average, String is_video, String overview, int id){
            Intent i = new Intent(context, MovieDetailActivity.class);
            i.putExtra("title", title);
            i.putExtra("poster_path", poster_path);
            i.putExtra("is_adult", is_adult);
            i.putExtra("backdrop_path", backdrop_path);
            i.putExtra("original_language", original_language);
            i.putExtra("popularity", popularity);
            i.putExtra("release_date", release_date);
            i.putExtra("vote_count", vote_count);
            i.putExtra("vote_average", vote_average);
            i.putExtra("is_video", is_video);
            i.putExtra("overview", overview);
            i.putExtra("id", id);
            context.startActivity(i);
        }
    }
    public void clear() {
        movies.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<SingleMovie> list) {
        movies.addAll(list);
        notifyDataSetChanged();
    }
}
