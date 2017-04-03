package com.rishabhshukla.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.model.SingleReview;

import java.util.List;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<SingleReview> reviews;
    private Context context;

    public ReviewAdapter(List<SingleReview> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_review, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.review.setText(reviews.get(position).getContent());
        holder.reviewer.setText("-" + reviews.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView review, reviewer;

        public ViewHolder(View itemView) {
            super(itemView);
            review = (TextView) itemView.findViewById(R.id.description);
            reviewer = (TextView) itemView.findViewById(R.id.reviewee);
        }
    }
}
