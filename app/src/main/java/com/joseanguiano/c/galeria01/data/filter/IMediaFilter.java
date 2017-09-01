package com.joseanguiano.c.galeria01.data.filter;

import com.joseanguiano.c.galeria01.data.Media;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public interface IMediaFilter {
    boolean accept(Media media);
}
