package com.motanad.motanad.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motanad.motanad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TubeFrag extends Fragment {

    private RecyclerView rvTube;

    private Context mContext;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tube, container, false);

        mContext = getActivity();
        initialize();
        return view;
    }

    private void initialize() {

        rvTube = (RecyclerView) view.findViewById(R.id.rvTube);


        Bitmap thumb = ThumbnailUtils.createVideoThumbnail("https://www.youtube.com/watch?v=kXKw2skbLdk",
                MediaStore.Images.Thumbnails.MINI_KIND);

        callApi();

    }

    private void callApi() {
        
    }

}
