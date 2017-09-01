package com.joseanguiano.c.galeria01.data.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class ImageFileFilter implements FilenameFilter {

    private Pattern pattern;

    public ImageFileFilter(boolean includeVideo) {
        pattern = includeVideo
                ? Pattern.compile(".(jpg|png|gif|jpe|jpeg|bmp|webp|mp4|mkv|webm|avi)$", Pattern.CASE_INSENSITIVE)
                : Pattern.compile(".(jpg|png|gif|jpe|jpeg|bmp|webp)$", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean accept(File dir, String filename) {
        return new File(dir, filename).isFile() && pattern.matcher(filename).find();
    }
}