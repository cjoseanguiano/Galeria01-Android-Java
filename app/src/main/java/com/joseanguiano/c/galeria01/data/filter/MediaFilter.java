package com.joseanguiano.c.galeria01.data.filter;

import com.joseanguiano.c.galeria01.data.Media;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class MediaFilter {
    public static IMediaFilter getFilter(FilterMode mode) {
        switch (mode) {
            case ALL: default:
                return media -> true;
            case VIDEO:
                return Media::isVideo;
            case IMAGES: return Media::isImage;
        }
    }
}
