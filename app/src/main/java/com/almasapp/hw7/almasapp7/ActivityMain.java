package com.almasapp.hw7.almasapp7;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityMain extends ActionBarActivity {
    private static final String TAG = "ActivityMain";

    private ArrayList<Map<String, ?>> moviesList;

    private DrawerLayout drawerLayout;
    private LinearLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = (new MovieData()).getMoviesList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        drawer = (LinearLayout) findViewById(R.id.drawer);

        RecyclerView drawerList = (RecyclerView) findViewById(R.id.drawer_list);
        drawerList.setLayoutManager(new LinearLayoutManager(this));

        DrawerRecyclerViewAdapter recyclerViewAdapter =
                new DrawerRecyclerViewAdapter(this, DrawerItemsLister.createDrawerList(this));
        drawerList.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnDrawerItemClickListener(new DrawerRecyclerViewAdapter.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(View view, int uniqueID) {
                selectItem(view, uniqueID);
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FragmentMain())
                .commit();
    }

    public void selectItem(View view, int uniqueID) {
        switch (uniqueID) {
            // Demo fragment
            case 0:
                break;

            // Movie list
            case 1:
                Log.d(TAG, "calling linear list");

                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentRecyclerView.newInstance(moviesList, FragmentRecyclerView.LAYOUT_LINEAR))
                    .commit();
                break;

            // Movie list (grid)
            case 2:
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentRecyclerView.newInstance(moviesList, FragmentRecyclerView.LAYOUT_GRID))
                    .commit();
                break;

        }

        drawerLayout.closeDrawer(drawer);
    }
}
