package com.joseanguiano.c.galeria01.data;

import android.content.Context;

import com.joseanguiano.c.galeria01.data.sort.SortingMode;
import com.joseanguiano.c.galeria01.data.sort.SortingOrder;
import com.orhanobut.hawk.Hawk;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */


public class AlbumsHelper {

    public static SortingMode getSortingMode(Context context) {
        return SortingMode.fromValue(Hawk.get("albums_sorting_mode", SortingMode.DATE.getValue()));
    }

    public static SortingOrder getSortingOrder(Context context) {
        return SortingOrder.fromValue(Hawk.get("albums_sorting_order", SortingOrder.DESCENDING.getValue()));
    }

}
