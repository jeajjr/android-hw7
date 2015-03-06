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

    public static final int ITEM_DEMO_FRAG = 0;
    public static final int ITEM_MOVIE_LIST = 1;
    public static final int ITEM_MOVIE_GRID = 2;
    public static final int ITEM_ABOUT_ME = 3;
    /*
    public static final int ITEM_DEMO_FRAG = 0;
    public static final int ITEM_DEMO_FRAG = 0;
*/
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

        list.add(createMenuItemWithIcon(ITEM_DEMO_FRAG, context.getResources().getString(R.string.demo_fragment), R.drawable.redirect_icon));
        list.add(createMenuItemWithIcon(ITEM_MOVIE_LIST, context.getResources().getString(R.string.movies_list), R.drawable.list_icon));
        list.add(createMenuItemWithIcon(ITEM_MOVIE_GRID, context.getResources().getString(R.string.movies_grid), R.drawable.grid_icon));

        list.add(creatOrganizingItem(DrawerRecyclerViewAdapter.LINE_SEPARATOR));

        list.add(createSimpleMenuItem(ITEM_ABOUT_ME, context.getString(R.string.about_me_title)));
        return list;
    }
}
