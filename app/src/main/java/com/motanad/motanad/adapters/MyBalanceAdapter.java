package com.motanad.motanad.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.activity.MyBalanceActivity;
import com.motanad.motanad.models.MyBalanceModel;

import java.util.ArrayList;

/**
 * Created by Admin on 9-12-17.
 */

public class MyBalanceAdapter extends RecyclerView.Adapter<MyBalanceAdapter.MyViewHolder> {

    private Context mContext;

    private ArrayList<MyBalanceModel> myBalanceList;


    public MyBalanceAdapter(Context mContext, ArrayList<MyBalanceModel> myBalanceList){
        this.mContext = mContext;
        this.myBalanceList = myBalanceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_balance_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MyBalanceModel balance = myBalanceList.get(position);
        holder.txtTitleBM.setText(balance.title);
        holder.txtDateMB.setText(balance.date);
        holder.txtPriceMB.setText(mContext.getString(R.string.rupee) + " " + balance.price);

        if (balance.isAdded){
            holder.imvImage.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_rupee_border));
            holder.imvAdd.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_add));
            holder.imvAddWithPrice.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_add));
        }
        else{
            holder.imvImage.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_withdraw));
            holder.imvAdd.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_share));
//            holder.imvAddWithPrice.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_share));
        }
    }

    @Override
    public int getItemCount() {
        return myBalanceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitleBM;
        public TextView txtDateMB;
        public TextView txtPriceMB;

        public Button btnStatus;

        public ImageView imvImage;
        public ImageView imvAdd;
        public ImageView imvAddWithPrice;

        public MyViewHolder(View view) {
            super(view);

            txtTitleBM = (TextView) view.findViewById(R.id.txtTitleBM);
            txtDateMB = (TextView) view.findViewById(R.id.txtDateMB);
            txtPriceMB = (TextView) view.findViewById(R.id.txtPriceMB);

            btnStatus = (Button) view.findViewById(R.id.btnStatus);

            imvImage = (ImageView) view.findViewById(R.id.imvImage);
            imvAdd = (ImageView) view.findViewById(R.id.imvAdd);
            imvAddWithPrice = (ImageView) view.findViewById(R.id.imvAddWithPrice);
        }
    }
}
