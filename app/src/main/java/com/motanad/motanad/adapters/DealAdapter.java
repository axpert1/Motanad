package com.motanad.motanad.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.motanad.motanad.R;
import com.motanad.motanad.models.DealModel;
import com.motanad.motanad.utilities.Util;

import java.util.ArrayList;

/**
 * Created by Admin on 11-12-17.
 */

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.MyViewHolder> {

    private Context mContext;

    private ArrayList<DealModel> dealList;

    public DealAdapter(Context mContext, ArrayList<DealModel> dealList ){
        this.mContext = mContext;
        this.dealList = dealList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deal_list_row, parent, false);

        int width = parent.getMeasuredWidth() / 2;
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(width, width);
        lp.setMargins(0, 0, 25, 10);
        itemView.setLayoutParams(lp);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DealModel deal = dealList.get(position);

        if (deal.image != null){
            String image = "http://wingstud.in/motanad/uploads/deals/"+deal.image;
            Util.setImage(mContext, holder.imvDealImage, image );
        }

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imvDealImage;

        public MyViewHolder(View view) {
            super(view);

            imvDealImage = (ImageView) view.findViewById(R.id.imvDealImage);

        }
    }
}
