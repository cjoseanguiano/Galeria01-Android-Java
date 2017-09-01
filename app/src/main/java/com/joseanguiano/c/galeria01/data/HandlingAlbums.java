package com.joseanguiano.c.galeria01.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.joseanguiano.c.galeria01.data.sort.SortingMode;
import com.joseanguiano.c.galeria01.data.sort.SortingOrder;
import com.joseanguiano.c.galeria01.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class HandlingAlbums extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "folders.db";
    private static final String TABLE_ALBUMS = "folders";

    public static final int EXCLUDED = 1;
    public static final int INCLUDED = 2;

    private static final String ALBUM_PATH = "path";
    private static final String ALBUM_ID = "id";
    private static final String ALBUM_PINNED = "pinned";
    private static final String ALBUM_COVER_PATH = "cover_path";
    private static final String ALBUM_STATUS = "status";
    private static final String ALBUM_SORTING_MODE = "sorting_mode";
    private static final String ALBUM_SORTING_ORDER = "sorting_order";

    private static final String backupFile = "albums2.bck";
    private static HandlingAlbums mInstance = null;

    private HandlingAlbums(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static HandlingAlbums getInstance(Context context) {
        if(mInstance == null)
            mInstance = new HandlingAlbums(context);
        return mInstance;
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_ALBUMS + "(" +
                ALBUM_PATH + " TEXT," +
                ALBUM_ID + " INTEGER," +
                ALBUM_PINNED + " INTEGER," +
                ALBUM_COVER_PATH + " TEXT, " +
                ALBUM_STATUS + " INTEGER, " +
                ALBUM_SORTING_MODE + " INTEGER, " +
                ALBUM_SORTING_ORDER + " INTEGER)");

        db.execSQL(String.format("CREATE UNIQUE INDEX idx_path ON %s (%s)", TABLE_ALBUMS, ALBUM_PATH));
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUMS);
        db.execSQL("DROP INDEX IF EXISTS idx_path");
        onCreate(db);
    }

    public ArrayList<String> getExcludedFolders(Context context) {
        ArrayList<String>  list = new ArrayList<>();
        HashSet<File> storageRoots = StorageHelper.getStorageRoots(context);
        for(File file : storageRoots)
            // it has a lot of garbage
            list.add(new File(file.getPath(), "Android").getPath());

        list.addAll(getFolders(EXCLUDED));
        return list;
    }

    public static ContentValues getDefaults(String path) {
        ContentValues values = new ContentValues();
        values.put(ALBUM_PATH, path);
        values.put(ALBUM_PINNED, 0);
        values.put(ALBUM_SORTING_MODE, SortingMode.DATE.getValue());
        values.put(ALBUM_SORTING_ORDER, SortingOrder.DESCENDING.getValue());
        values.put(ALBUM_ID, -1);
        return values;
    }

    /**
     *
     * @param status 1 for EXCLUDED, 2 for INCLUDED
     * @return
     */
    public ArrayList<String> getFolders(int status) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.query(TABLE_ALBUMS, new String[]{ALBUM_PATH}, ALBUM_STATUS + "=?", new String[]{String.valueOf(status)}, null, null, null);
        if (cur.moveToFirst())
            do list.add(cur.getString(0)); while (cur.moveToNext());
        cur.close();
        db.close();
        return list;
    }

    private static boolean exist(SQLiteDatabase db, String path) {
        Cursor cur = db.rawQuery(
                String.format("SELECT EXISTS(SELECT 1 FROM %s WHERE %s=? LIMIT 1)", TABLE_ALBUMS, ALBUM_PATH),
                new String[]{ path });
        boolean tracked = cur.moveToFirst() &&  cur.getInt(0) == 1;
        cur.close();
        return  tracked;
    }

    public void setCover(String path, String mediaPath) {
        ContentValues values = new ContentValues();
        values.put(ALBUM_COVER_PATH, mediaPath);
        setValue(path, values);
    }
    private void setValue(String path, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_ALBUMS, values, ALBUM_PATH+"=?", new String[]{ path });
        db.close();
    }

    @NonNull
    public static AlbumSettings getSettings(SQLiteDatabase db, String path) {
        Cursor cursor = null;
        try {

            if (exist(db, path)) {
                cursor = db.query(
                        TABLE_ALBUMS,
                        StringUtils.asArray(
                                ALBUM_COVER_PATH,
                                ALBUM_SORTING_MODE,
                                ALBUM_SORTING_ORDER,
                                ALBUM_PINNED),
                        ALBUM_PATH + "=?",
                        new String[]{path},
                        null, null, null);

                if (cursor.moveToFirst())
                    return new AlbumSettings(
                            cursor.getString(0),
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3));
            } else
                db.insert(
                        TABLE_ALBUMS,
                        null,
                        getDefaults(path));

            return AlbumSettings.getDefaults();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }
}
