package com.almasapp.hw7.almasapp7;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
/**
 * Created by José Ernesto on 05/03/2015.
 */
public class DrawerRecyclerViewAdapter extends RecyclerView.Adapter<DrawerRecyclerViewAdapter.ViewHolder> {
    public static final int ITEM_WITH_ICON = 0;
    public static final int ITEM_SIMPLE = 1;
    public static final int LINE_SEPARATOR = 2;
    public static final int SPACER = 3;

    private static String TAG = "DrawerRecyclerViewAdapter";

    private List<Map<String, ?>> dataSet;
    private OnDrawerItemClickListener onDrawerItemClickListener;
    private int currentItem;

    public DrawerRecyclerViewAdapter(Context context, List<Map<String, ?>> dataSet) {
        this.dataSet = dataSet;
    }

    public interface OnDrawerItemClickListener {
        public void onItemClick(View view, int uniqueID);
    }

    public void setOnDrawerItemClickListener(final OnDrawerItemClickListener mItemClickListener) {
        this.onDrawerItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;
        View currentView;

        public ViewHolder(View v) {
            super(v);

            currentView = v;

            icon = (ImageView) v.findViewById(R.id.itemIcon);
            name = (TextView) v.findViewById(R.id.itemName);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (Integer) dataSet.get(getPosition()).get("id");
                    Log.d(TAG, "adapter received click on item " + id);

                    if ((Integer) dataSet.get(getPosition()).get("type") == ITEM_WITH_ICON ||
                            (Integer) dataSet.get(getPosition()).get("type") == ITEM_SIMPLE) {
                        if (onDrawerItemClickListener != null)
                            onDrawerItemClickListener.onItemClick(v, id);

                        currentItem = getPosition();
                        notifyDataSetChanged();
                    }
                }
            });
        }

        public void bindData (Map<String, ?> item, int position) {
            switch ((Integer) item.get("type")) {
                case ITEM_SIMPLE:
                    name.setText((String) item.get("name"));
                    break;
                case ITEM_WITH_ICON:
                    name.setText((String) item.get("name"));
                    icon.setImageResource((Integer) item.get("icon"));
                    break;
            }

            if (currentItem == position) {
                currentView.setBackgroundColor(0xFF2496d4);
            }
            else
                currentView.setBackgroundColor(0x00000000);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return (Integer) dataSet.get(position).get("type");
    }

    @Override
    public DrawerRecyclerViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = null;

        switch (viewType) {
            case  ITEM_WITH_ICON:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_icon, parent, false);
                break;
            case ITEM_SIMPLE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_simple, parent, false);
                break;
            case LINE_SEPARATOR:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_separator, parent, false);
                break;
            case SPACER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_8dp_space, parent, false);
        }

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        Map<String, ?> item = dataSet.get(position);
        holder.bindData(item, position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}