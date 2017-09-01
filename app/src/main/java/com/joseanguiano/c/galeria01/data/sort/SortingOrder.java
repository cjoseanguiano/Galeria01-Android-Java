package com.joseanguiano.c.galeria01.data.sort;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public enum SortingOrder {
    ASCENDING (1), DESCENDING (0);

    int value;

    SortingOrder(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAscending(){
        return value == ASCENDING.getValue();
    }

    public static SortingOrder fromValue(boolean value) {
        return value ? ASCENDING : DESCENDING;
    }

    public static SortingOrder fromValue(int value) {
        return value == 0 ? DESCENDING : ASCENDING;
    }
}
