package com.joseanguiano.c.galeria01.data.sort;

import com.joseanguiano.c.galeria01.data.Media;

import java.util.Comparator;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class MediaComparators {

    public static Comparator<Media> getComparator(SortingMode sortingMode, SortingOrder sortingOrder) {
        return  sortingOrder == SortingOrder.ASCENDING
                ? getComparator(sortingMode) : reverse(getComparator(sortingMode));
    }

    public static Comparator<Media> getComparator(SortingMode sortingMode) {
        switch (sortingMode) {
            case DATE: default: return getDateComparator();
            case TYPE: return getTypeComparator();
        }
    }

    private static Comparator<Media> reverse(Comparator<Media> comparator) {
        return (o1, o2) -> comparator.compare(o2, o1);
    }

    private static Comparator<Media> getDateComparator(){
        return (f1, f2) -> f1.getDateModified().compareTo(f2.getDateModified());
    }
    private static Comparator<Media> getTypeComparator() {
        return (f1, f2) -> f1.getMimeType().compareTo(f2.getMimeType());
    }

}
