package com.motanad.motanad.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.models.DealModel;
import com.motanad.motanad.models.TubeModel;

import java.util.ArrayList;

/**
 * Created by Admin on 12-12-17.
 */

public class TubeAdapter extends RecyclerView.Adapter<TubeAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<TubeModel> tubeList;

    public TubeAdapter(Context mContext, ArrayList<TubeModel> tubeList ){
        this.mContext = mContext;
        this.tubeList = tubeList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tube_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TubeModel tube = tubeList.get(position);

        holder.txtVideoName.setText(tube.video_title);

        String imageUrl = tube.video_url;

        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageUrl,
                MediaStore.Images.Thumbnails.MINI_KIND);


    }

    @Override
    public int getItemCount() {
        return tubeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtVideoName;

        public ImageView imvVideoImage;

        public MyViewHolder(View view) {
            super(view);

            txtVideoName = (TextView) view.findViewById(R.id.txtVideoName);

            imvVideoImage = (ImageView) view.findViewById(R.id.imvVideoImage);

        }
    }
}
