package com.rishabhshukla.popularmoviesapp.controller;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.model.SingleMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 30/03/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<SingleMovie> movies;
    private Context context;
    private SwipeRefreshLayout swipeContainer;

    public MovieAdapter(ArrayList<SingleMovie> movieArrayList){
        movies = movieArrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(movies.get(position).getOriginalTitle());
        String poster = "http://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath();
        //TODO : remove fit and check what happens
        Picasso.with(context)
                .load(poster)
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.movie_name);
            imageView = (ImageView) itemView.findViewById(R.id.movie_image);
        }
    }
}
