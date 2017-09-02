package com.joseanguiano.c.galeria01.activities;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.joseanguiano.c.galeria01.BuildConfig;
import com.joseanguiano.c.galeria01.R;
import com.joseanguiano.c.galeria01.activities.base.SharedMediaActivity;
import com.joseanguiano.c.galeria01.data.Album;

import com.joseanguiano.c.galeria01.fragments.BaseFragment;
import com.joseanguiano.c.galeria01.fragments.PhotoAlbumFragment;
import com.joseanguiano.c.galeria01.fragments.RvMediaFragmentPhoto;
import com.joseanguiano.c.galeria01.fragments.VideoAlbumFragment;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;

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

       /* ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);*/

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




        @BindView(R.id.drawer_layout)
        DrawerLayout drawer;
        @BindView(R.id.toolbar)
        Toolbar toolbar;
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

        public void goBackToAlbums() {
            albumsMode = true;
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportFragmentManager().popBackStack();
        }

        private void initUi() {


            displayMedia(Album.getAllMediaAlbum());

        }

        @Override
        protected void onPostResume() {
            super.onPostResume();
        }



/*        public void updateToolbar(String title, IIcon icon, View.OnClickListener onClickListener) {
            updateToolbar(title, icon);
            toolbar.setNavigationOnClickListener(onClickListener);
        }*/

/*
        public void updateToolbar(String title, IIcon icon) {
            toolbar.setTitle(title);
            toolbar.setNavigationIcon(getToolbarIcon(icon));
        }

        public void resetToolbar() {
            updateToolbar(
                    getString(R.string.app_name),
                    GoogleMaterial.Icon.gmd_menu,
                    v -> drawer.openDrawer(GravityCompat.START));
        }

*/

        @Override
        public void onBackPressed() {

            if (albumsMode) {
                if (!albumsFragment.onBackPressed()) {
                    if (drawer.isDrawerOpen(GravityCompat.START))
                        drawer.closeDrawer(GravityCompat.START);
                    else finish();
                }
            } else {
                if (!((BaseFragment) getSupportFragmentManager().findFragmentByTag("media")).onBackPressed())
                    goBackToAlbums();
            }
        }

}