package com.motanad.motanad.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.R;
import com.motanad.motanad.activity.Dashboard;
import com.motanad.motanad.activity.LevelAcitvity;
import com.motanad.motanad.adapters.NetworkAdapter;
import com.motanad.motanad.loopjServcice.CmdParams;
import com.motanad.motanad.loopjServcice.JsonDeserializer;
import com.motanad.motanad.loopjServcice.NetworkManager;
import com.motanad.motanad.modelTemp.NetworkTemp;
import com.motanad.motanad.modelTemp.RegisterTemp;
import com.motanad.motanad.models.NetworkModel;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.utilities.AppUrls;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;
import com.motanad.motanad.utilities.recycler_view_utilities.DividerItemDecorationColorPrimary;
import com.motanad.motanad.utilities.recycler_view_utilities.DividerItemDecorationGray;
import com.motanad.motanad.utilities.recycler_view_utilities.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFrag extends Fragment implements NetworkManager.onCallback {

    private TextView txtUNameNetwork;
    private TextView txtPriceNetwork;

    private RecyclerView rvNetwork;

    private NetworkAdapter mAdapter;
    private ArrayList<NetworkModel> networkList;

    private Context mContext;

    private View view;

    private long userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.network, container, false);

        mContext = getActivity();

        initialize();

        return view;
    }

    private void initialize() {
        txtUNameNetwork = (TextView) view.findViewById(R.id.txtUNameNetwork);
        txtPriceNetwork = (TextView) view.findViewById(R.id.txtPriceNetwork);

        rvNetwork = (RecyclerView) view.findViewById(R.id.rvNetwork);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvNetwork.setLayoutManager(mLayoutManager);
        rvNetwork.setItemAnimator(new DefaultItemAnimator());
        rvNetwork.addItemDecoration(new DividerItemDecorationColorPrimary(mContext));

        rvNetwork.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (networkList.get(position).total > 0) {
                    Intent in = new Intent(getActivity(), LevelAcitvity.class);
                    in.putExtra(Constants.FLD_LEVEL, networkList.get(position).level);
                    startActivity(in);
                }

            }
        }));

        callApi();
    }

    private void callApi() {
        String apiTitle = "Loading...";
        userId = SharedPref.getUserId(mContext);
        RequestParams params = CmdParams.CreateUserId(""+ userId);
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, Constants.VAL_POST, AppUrls.USER_GET_USER_NETWORK_URL + "/" +Constants.TOKEN_ID, params, apiTitle, this, true, 0);
    }


    @Override
    public void onSuccess(boolean success, String response, int which) {
        NetworkTemp networkTemp = JsonDeserializer.deserializeJson(response, NetworkTemp.class);
        if (networkTemp.status == 1){

            networkList = networkTemp.data;
            setData();
        }
        else {
            Util.showDefaultAlert(mContext, networkTemp.message, null);
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Toast.makeText(mContext, "Failure", Toast.LENGTH_SHORT).show();
    }

    private void setData() {
        mAdapter = new NetworkAdapter(mContext, networkList);
        rvNetwork.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
