package com.rishabhshukla.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rishabhshukla.popularmoviesapp.R;
import com.rishabhshukla.popularmoviesapp.model.SingleVideo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private String path;
    private List<SingleVideo> videos;
    private Context context;

    public VideoAdapter(List<SingleVideo> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        path = "https://img.youtube.com/vi/" + videos.get(position).getKey() + "/mqdefault.jpg";
        Picasso.with(context).load(path).fit().into(holder.backDrop);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView backDrop;
        public RelativeLayout clickView;

        public ViewHolder(View itemView) {
            super(itemView);
            backDrop = (ImageView) itemView.findViewById(R.id.back_drop);
            clickView = (RelativeLayout) itemView.findViewById(R.id.click_view);
        }
    }
}
