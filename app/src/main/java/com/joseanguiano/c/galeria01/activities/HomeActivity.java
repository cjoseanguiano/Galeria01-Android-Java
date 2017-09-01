package com.joseanguiano.c.galeria01.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.joseanguiano.c.galeria01.R;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private Button button;
    private Button fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = findViewById(R.id.buttonHome);
        fragments = findViewById(R.id.fragments);

        button.setOnClickListener(view -> {
            Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            start();
        });

        fragments.setOnClickListener(view -> startFragments() );
        ButterKnife.bind(this);

        if (savedInstanceState != null)
            return;
    }

    private void startFragments() {
        startActivity(new Intent(HomeActivity.this, MainAlbumListActivity.class));
        finish();    }

    private void start() {
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();
    }
}
