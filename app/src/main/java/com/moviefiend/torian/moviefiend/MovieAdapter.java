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
        holder.setTitle("Kings landing");
        holder.setPosterView("http://awoiaf.westeros.org/images/thumb/6/64/Tomasz_Jedruszek_Kings_Landing.jpg/400px-Tomasz_Jedruszek_Kings_Landing.jpg");
        holder.setRating(3.5f);
    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
