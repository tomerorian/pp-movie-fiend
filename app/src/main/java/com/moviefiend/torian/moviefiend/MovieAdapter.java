package com.moviefiend.torian.moviefiend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item_view, parent, false);

        return new MovieViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
