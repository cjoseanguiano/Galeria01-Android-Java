package com.joseanguiano.c.galeria01.data;

import android.database.Cursor;

import java.sql.SQLException;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public interface CursorHandler<T> {
    T handle(Cursor cu) throws SQLException;
    static String [] getProjection() {
        return new String[0];
    }
}
