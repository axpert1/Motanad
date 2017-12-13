package com.motanad.motanad.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.R;
import com.motanad.motanad.activity.LevelAcitvity;
import com.motanad.motanad.adapters.DealAdapter;
import com.motanad.motanad.adapters.NetworkAdapter;
import com.motanad.motanad.loopjServcice.CmdParams;
import com.motanad.motanad.loopjServcice.JsonDeserializer;
import com.motanad.motanad.loopjServcice.NetworkManager;
import com.motanad.motanad.modelTemp.DealTemp;
import com.motanad.motanad.modelTemp.NetworkTemp;
import com.motanad.motanad.models.DealModel;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.utilities.AppUrls;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;
import com.motanad.motanad.utilities.recycler_view_utilities.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DealFrag extends Fragment implements NetworkManager.onCallback {

    private RecyclerView rvDeals;

    private Context mContext;

    private DealAdapter mAdapter;
    private ArrayList<DealModel> dealList;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.deal, container, false);

        mContext = getActivity();
        initialize();

        return view;
    }

    private void initialize() {
        rvDeals = (RecyclerView) view.findViewById(R.id.rvDeals);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        rvDeals.setLayoutManager(mLayoutManager);
        rvDeals.setItemAnimator(new DefaultItemAnimator());

        rvDeals.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Uri uri = Uri.parse("http://"+dealList.get(position).affiliated_link); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }));

        callApi();

    }

    private void callApi() {
        String apiTitle = "Loading...";
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, Constants.VAL_POST, AppUrls.USER_GET_DEALS_LIST + "/" +Constants.TOKEN_ID, null, apiTitle, this, true, 0);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        DealTemp dealTemp = JsonDeserializer.deserializeJson(response, DealTemp.class);
        if (dealTemp.status == 1){

            dealList = dealTemp.data;
            setData();
        }
        else {
            Util.showDefaultAlert(mContext, dealTemp.message, null);
        }
    }

    private void setData() {
        mAdapter = new DealAdapter(mContext, dealList);
        rvDeals.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

    }
}
