package com.joseanguiano.c.galeria01.activities;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.joseanguiano.c.galeria01.R;
import com.joseanguiano.c.galeria01.activities.base.SharedMediaActivity;
import com.joseanguiano.c.galeria01.data.Album;
import com.joseanguiano.c.galeria01.fragments.PhotoAlbumFragment;
import com.joseanguiano.c.galeria01.fragments.RvMediaFragmentPhoto;
import com.joseanguiano.c.galeria01.fragments.VideoAlbumFragment;

import butterknife.BindView;

public class MainAlbumListActivity extends SharedMediaActivity {
    public static final String TAG = MainAlbumListActivity.class.getSimpleName();
    private boolean albumsMode = true;
    PhotoAlbumFragment albumsFragment = new PhotoAlbumFragment();
    private boolean pickMode = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_album_list);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        initUi();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, albumsFragment, "albums")
                .commit();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String tabFoto = "Foto";
        String tabVideo = "Video";

        adapter.addFragment(new PhotoAlbumFragment(), tabFoto);
        adapter.addFragment(new VideoAlbumFragment(), tabVideo);
        viewPager.setAdapter(adapter);
    }

    @BindView(R.id.coordinator_main_layout)
    CoordinatorLayout mainLayout;


    public void displayMedia(Album album) {
        albumsMode = false;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, RvMediaFragmentPhoto.make(album), "media")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initUi() {


        displayMedia(Album.getAllMediaAlbum());

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

}