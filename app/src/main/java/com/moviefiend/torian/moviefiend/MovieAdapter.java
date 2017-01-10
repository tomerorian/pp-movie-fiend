package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context mContext;
    private ArrayList<NowPlayingResponse.MovieInfo> mMovies;

    public MovieAdapter(Context context) {
        this.mContext = context;

        mMovies = new ArrayList<>();
    }

    public void setMovies(ArrayList<NowPlayingResponse.MovieInfo> movies) {
        this.mMovies = movies;
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
        NowPlayingResponse.MovieInfo movie = mMovies.get(position);
        holder.setTitle(movie.getTitle());
        holder.setPosterView(mContext.getString(R.string.tmdb_poster_url, movie.getPosterPath()));
        holder.setRating(movie.getRating());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
