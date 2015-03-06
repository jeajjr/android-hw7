package com.almasapp.hw7.almasapp7;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

public class FragmentRecyclerView extends Fragment {
    static String TAG = "FragmentRecyclerView";
    static String ARG_MOVIE_LIST = "movie_list";
    static String ARG_LAYOUT = "layout";

    public static final int LAYOUT_LINEAR = 0;
    public static final int LAYOUT_GRID = 1;
    public static final int LAYOUT_STAGGERED_GRID = 2;

    private int layout;
    private ArrayList<Map<String, ?>> movieList;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private RecyclerView moviesRecyclerView;

    private OnItemClickedListener mListener;

    public interface OnItemClickedListener {
        public void onItemClick(int position);
    }

    public FragmentRecyclerView() {
    }

    public static FragmentRecyclerView newInstance(ArrayList<Map<String, ?>> movieList, int layout) {
        FragmentRecyclerView fragment = new FragmentRecyclerView();

        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE_LIST, movieList);
        args.putInt(ARG_LAYOUT, layout);
        fragment.setArguments(args);

        return fragment;
    }

    private int findInMovies (String search) {
        for (int index = 0; index < movieList.size(); index++)
            if (((String) movieList.get(index).get("name")).toLowerCase().contains(search.toLowerCase()))
                return index;

        return -1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            movieList = (ArrayList<Map<String, ?>>) bundle.get(ARG_MOVIE_LIST);
            layout = bundle.getInt(ARG_LAYOUT);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        setHasOptionsMenu(true);

        moviesRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        moviesRecyclerView.setHasFixedSize(true);

        switch (layout) {
            case LAYOUT_LINEAR:
                moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), movieList, LAYOUT_LINEAR);
                break;

            case LAYOUT_GRID:
                moviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), movieList, LAYOUT_GRID);
                break;

            case LAYOUT_STAGGERED_GRID:
                moviesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), movieList, LAYOUT_STAGGERED_GRID);
                break;
        }
        moviesRecyclerView.setAdapter(movieRecyclerViewAdapter);

        return rootView;
    }

}