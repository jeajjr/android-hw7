package com.almasapp.hw7.almasapp7;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
/**
 * Created by José Ernesto on 05/03/2015.
 */
/**
 * Created by José Ernesto on 12/02/2015.
 */
public class DrawerRecyclerViewAdapter extends RecyclerView.Adapter<DrawerRecyclerViewAdapter.ViewHolder> {
    private static String TAG = "MyRecyclerViewAdapter";

    private List<Map<String, ?>> mDataSet;
    private Context context;
    private int layout;

    public DrawerRecyclerViewAdapter(Context context, List<Map<String, ?>> mDataSet) {
        this.mDataSet = mDataSet;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public ImageButton menuButton;
        public RatingBar ratingBar;

        public ViewHolder(View v) {
            super(v);

        }

        public void bindMovieData (Map<String, ?> movie) {

        }
    }



    @Override
    public DrawerRecyclerViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = null;

        //v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row_linear, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Map<String, ?> movie = mDataSet.get(position);
        holder.bindMovieData(movie);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}