package com.motanad.motanad.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.models.LevelModel;

import java.util.ArrayList;

/**
 * Created by Admin on 11-12-17.
 */

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<LevelModel> levelList;
    private View.OnClickListener callButtonClick;

    public LevelAdapter(Context mContext, ArrayList<LevelModel> levelList, View.OnClickListener callButtonClick){
        this.mContext = mContext;
        this.levelList = levelList;
        this.callButtonClick = callButtonClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.level_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LevelModel level = levelList.get(position);

        holder.txtLevelUName.setText(level.name);
        holder.txtLevelMovileNo.setText(level.mobileNo);

        if (!level.isOnline){
            holder.txtIndicator.setBackground(mContext.getResources().getDrawable(R.drawable.rounded_btn_bg_red));
        }

        holder.fabtnCall.setTag(position);
        holder.fabtnCall.setOnClickListener(callButtonClick);
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtLevelUName;
        public TextView txtLevelMovileNo;
        public TextView txtIndicator;

        public FloatingActionButton fabtnCall;

        public MyViewHolder(View view) {
            super(view);
            txtLevelUName = (TextView) view.findViewById(R.id.txtLevelUName);
            txtLevelMovileNo = (TextView) view.findViewById(R.id.txtLevelMovileNo);
            txtIndicator = (TextView) view.findViewById(R.id.txtIndicator);

            fabtnCall = (FloatingActionButton) view.findViewById(R.id.fabtnCall);

        }
    }
}
