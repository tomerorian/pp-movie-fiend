package com.moviefiend.torian.moviefiend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MovieListFragment extends Fragment {
    private static final int GRID_SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.movie_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_SPAN_COUNT));
        mRecyclerView.setAdapter(new MovieAdapter());

        return view;
    }
}
