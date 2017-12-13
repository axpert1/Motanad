package com.motanad.motanad.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.models.NetworkModel;

import java.util.ArrayList;

/**
 * Created by Admin on 11-12-17.
 */

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.MyViewHolder> {

    private Context mContext;

    private ArrayList<NetworkModel> networkList;

    public NetworkAdapter(Context mContext, ArrayList<NetworkModel> networkList){
        this.mContext = mContext;
        this.networkList = networkList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.network_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NetworkModel network = networkList.get(position);
        holder.txtLevels.setText(network.level);
        holder.txtTotalNetwork.setText(String.valueOf(network.total));

        if (networkList.get(position).total > 0){
            holder.imvNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return networkList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtLevels;
        public TextView txtTotalNetwork;

        public ImageView imvNext;

        public MyViewHolder(View view) {
            super(view);
            txtLevels = (TextView) view.findViewById(R.id.txtLevels);
            txtTotalNetwork = (TextView) view.findViewById(R.id.txtTotalNetwork);

            imvNext = (ImageView) view.findViewById(R.id.imvNext);
        }
    }
}
