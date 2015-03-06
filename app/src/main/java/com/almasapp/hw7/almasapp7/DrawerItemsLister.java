package com.almasapp.hw7.almasapp7;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by José Ernesto on 06/03/2015.
 */
public class DrawerItemsLister {

    private static HashMap createMenuItem (int type) {
        HashMap item = new HashMap();

        item.put("type", type);

        return item;
    }

    private static HashMap createSimpleMenuItem(String name) {
        HashMap item = new HashMap();

        item.put("name", name);
        item.put("type", DrawerRecyclerViewAdapter.ITEM_SIMPLE);

        return item;
    }

    private static HashMap createMenuItemWithIcon(String name, int drawableID) {
        HashMap item = new HashMap();

        item.put("name", name);
        item.put("icon", drawableID);
        item.put("type", DrawerRecyclerViewAdapter.ITEM_WITH_ICON);

        return item;
    }

    public static List<Map<String, ?>> createDrawerList(Context context) {
        ArrayList<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

        list.add(createMenuItem(DrawerRecyclerViewAdapter.SPACER));
        list.add(createMenuItem(DrawerRecyclerViewAdapter.SPACER));

        list.add(createMenuItemWithIcon(context.getResources().getString(R.string.demo_fragment), R.drawable.redirect_icon));
        list.add(createMenuItemWithIcon(context.getResources().getString(R.string.movies_list), R.drawable.list_icon));
        list.add(createMenuItemWithIcon(context.getResources().getString(R.string.movies_grid), R.drawable.grid_icon));

        list.add(createMenuItem(DrawerRecyclerViewAdapter.LINE_SEPARATOR));

        list.add(createSimpleMenuItem("flango"));
        return list;
    }
}
