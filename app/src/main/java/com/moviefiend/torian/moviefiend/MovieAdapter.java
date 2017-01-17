package com.moviefiend.torian.moviefiend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MoviesResponse.MovieInfo> mMovies;
    private MovieViewHolder.MovieClickListener mClickListener;

    public MovieAdapter() {
        mMovies =  new ArrayList<>();
    }

    // <editor-fold desc="RecyclerView.Adapter">
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item_view, parent, false);

        return new MovieViewHolder(itemView, mClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
    // </editor-fold>

    public void setClickListener(MovieViewHolder.MovieClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setMovies(List<MoviesResponse.MovieInfo> movies) {
        if (movies != null) {
            this.mMovies = movies;
        } else {
            this.mMovies = new ArrayList<>();
        }

        notifyDataSetChanged();
    }
}
