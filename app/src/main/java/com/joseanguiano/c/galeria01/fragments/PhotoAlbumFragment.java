package com.joseanguiano.c.galeria01.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joseanguiano.c.galeria01.R;

@SuppressLint("ValidFragment")
public class PhotoAlbumFragment extends Fragment {

    private static final String TAG = PhotoAlbumFragment.class.getSimpleName();
    private View view = null;

    public PhotoAlbumFragment() {
    }

    public interface OnMediaSelectedPhotoAlbum {
        void onMediaSelected(String message, String type, boolean backPressed);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            OnMediaSelectedPhotoAlbum mCallback = (OnMediaSelectedPhotoAlbum) context;
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
        view = inflater.inflate(R.layout.fragment_photo_album, container, false);
        return view;

    }

}
