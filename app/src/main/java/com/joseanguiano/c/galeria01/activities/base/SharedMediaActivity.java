package com.joseanguiano.c.galeria01.activities.base;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.joseanguiano.c.galeria01.App;
import com.joseanguiano.c.galeria01.R;
import com.joseanguiano.c.galeria01.data.Album;
import com.joseanguiano.c.galeria01.data.HandlingAlbums;
import com.joseanguiano.c.galeria01.data.StorageHelper;

import horaapps.org.liz.ThemedActivity;


public abstract class SharedMediaActivity extends ThemedActivity {

    private int REQUEST_CODE_SD_CARD_PERMISSIONS = 42;

    @Deprecated
    public HandlingAlbums getAlbums() {
        return ((App) getApplicationContext()).getAlbums();
    }

    @Deprecated
    public Album getAlbum() {
        return ((App) getApplicationContext()).getAlbum();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent resultData) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SD_CARD_PERMISSIONS) {
                Uri treeUri = resultData.getData();
                // Persist URI in shared preference so that you can use it later.
                StorageHelper.saveSdCardInfo(getApplicationContext(), treeUri);
                getContentResolver().takePersistableUriPermission(treeUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Toast.makeText(this, R.string.got_permission_wr_sdcard, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
