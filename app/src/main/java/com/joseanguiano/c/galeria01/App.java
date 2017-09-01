package com.joseanguiano.c.galeria01;

import android.app.Application;

import com.joseanguiano.c.galeria01.data.Album;
import com.joseanguiano.c.galeria01.data.HandlingAlbums;
import com.orhanobut.hawk.Hawk;

/**
 * Created by Carlos Anguiano on 01/09/17.
 * For more info contact: c.joseanguiano@gmail.com
 */

public class App extends /*horaapps.org.liz.App*/ Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
/*
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
*/

        Hawk.init(this).build();

        mInstance = this;
    }

    public static App getInstance() {
        return mInstance;
    }

    @Deprecated
    public Album getAlbum() {
        return Album.getEmptyAlbum();
    }

    @Deprecated
    public HandlingAlbums getAlbums() {
        return HandlingAlbums.getInstance(getApplicationContext());
    }
}