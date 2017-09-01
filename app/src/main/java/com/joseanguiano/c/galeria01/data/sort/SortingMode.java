package com.joseanguiano.c.galeria01.data.sort;

import android.provider.MediaStore;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public enum SortingMode {
    DATE (1, MediaStore.MediaColumns.DATE_MODIFIED, "max(" + MediaStore.Images.Media.DATE_MODIFIED + ")"),
    TYPE(3, MediaStore.MediaColumns.MIME_TYPE);

    int value;
    String mediaColumn;
    String albumsColumn;

    SortingMode(int value, String mediaColumn) {
        this.value = value;
        this.mediaColumn = mediaColumn;
        this.albumsColumn = mediaColumn;
    }

    SortingMode(int value, String mediaColumn, String albumsColumn) {
        this.value = value;
        this.mediaColumn = mediaColumn;
        this.albumsColumn = albumsColumn;
    }

    public String getMediaColumn() {
        return mediaColumn;
    }

    public String getAlbumsColumn() {
        return albumsColumn;
    }

    public int getValue() {
        return value;
    }

    public static SortingMode fromValue(int value) {
        switch (value) {
            case 1: default: return DATE;
            case 3: return TYPE;
        }
    }
}
