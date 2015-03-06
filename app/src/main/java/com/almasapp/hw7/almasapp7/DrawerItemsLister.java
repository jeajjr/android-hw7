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

    private static HashMap creatOrganizingItem(int type) {
        HashMap item = new HashMap();

        item.put("id", -1);
        item.put("type", type);

        return item;
    }

    private static HashMap createSimpleMenuItem(int uniqueID, String name) {
        HashMap item = new HashMap();

        item.put("id", uniqueID);
        item.put("name", name);
        item.put("type", DrawerRecyclerViewAdapter.ITEM_SIMPLE);

        return item;
    }

    private static HashMap createMenuItemWithIcon(int uniqueID, String name, int drawableID) {
        HashMap item = new HashMap();

        item.put("id", uniqueID);
        item.put("name", name);
        item.put("icon", drawableID);
        item.put("type", DrawerRecyclerViewAdapter.ITEM_WITH_ICON);

        return item;
    }

    public static List<Map<String, ?>> createDrawerList(Context context) {
        ArrayList<Map<String, ?>> list = new ArrayList<Map<String, ?>>();

        list.add(creatOrganizingItem(DrawerRecyclerViewAdapter.SPACER));
        list.add(creatOrganizingItem(DrawerRecyclerViewAdapter.SPACER));

        list.add(createMenuItemWithIcon(0, context.getResources().getString(R.string.demo_fragment), R.drawable.redirect_icon));
        list.add(createMenuItemWithIcon(1, context.getResources().getString(R.string.movies_list), R.drawable.list_icon));
        list.add(createMenuItemWithIcon(2, context.getResources().getString(R.string.movies_grid), R.drawable.grid_icon));

        list.add(creatOrganizingItem(DrawerRecyclerViewAdapter.LINE_SEPARATOR));

        list.add(createSimpleMenuItem(3, "flango"));
        return list;
    }
}
