package com.joseanguiano.c.galeria01.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joseanguiano.c.galeria01.R;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class VideoAlbumFragment extends Fragment {

    private static final String TAG = VideoAlbumFragment.class.getSimpleName();
    private View view = null;

    public interface OnMediaSelectedVideoAlbum {
        void onMediaSelected(String message, String type, boolean backPressed);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            OnMediaSelectedVideoAlbum mCallback = (OnMediaSelectedVideoAlbum) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ProgressBar");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_album, container, false);
        return view;

    }

}

