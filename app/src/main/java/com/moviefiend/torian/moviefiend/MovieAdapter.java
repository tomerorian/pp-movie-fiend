package com.moviefiend.torian.moviefiend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<NowPlayingResponse.MovieInfo> mMovies;

    public MovieAdapter() {
        mMovies =  new ArrayList<>();
    }

    public void setMovies(List<NowPlayingResponse.MovieInfo> movies) {
        if (movies != null) {
            this.mMovies = movies;
        } else {
            this.mMovies = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item_view, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
