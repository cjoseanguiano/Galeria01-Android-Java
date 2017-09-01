package com.joseanguiano.c.galeria01.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.joseanguiano.c.galeria01.BuildConfig;
import com.joseanguiano.c.galeria01.R;
import com.joseanguiano.c.galeria01.data.Album;
import com.joseanguiano.c.galeria01.fragments.PhotoAlbumFragment;
import com.joseanguiano.c.galeria01.fragments.RvMediaFragment;
import com.joseanguiano.c.galeria01.fragments.VideoAlbumFragment;

public class MainAlbumListActivity extends AppCompatActivity implements PhotoAlbumFragment.OnMediaSelectedPhotoAlbum, VideoAlbumFragment.OnMediaSelectedVideoAlbum {
    public static final String TAG = MainAlbumListActivity.class.getSimpleName();
    private boolean albumsMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_album_list);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        initUi();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String tabFoto = "Foto";
        String tabVideo = "Video";

        adapter.addFragment(new PhotoAlbumFragment(), tabFoto);
        adapter.addFragment(new VideoAlbumFragment(), tabVideo);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMediaSelected(String message, String type, boolean backPressed) {
        Log.i(TAG, "onMediaSelected: ");
    }

    public void displayMedia(Album album) {
        albumsMode = false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, RvMediaFragment.make(album), "media")
                .addToBackStack(null)
                .commit();
    }

    private void initUi() {
        displayMedia(Album.getAllMediaAlbum());
    }
}