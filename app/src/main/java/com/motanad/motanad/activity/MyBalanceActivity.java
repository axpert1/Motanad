package com.motanad.motanad.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.adapters.MyBalanceAdapter;
import com.motanad.motanad.models.MyBalanceModel;

import java.util.ArrayList;

public class MyBalanceActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView title;
    private TextView txtTotalAmount;

    private RecyclerView rvMyBalance;

    private Context mContext;

    private MyBalanceAdapter mAdapter;
    private ArrayList<MyBalanceModel> myBalanceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_balance);

        mContext = MyBalanceActivity.this;
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        txtTotalAmount = (TextView) findViewById(R.id.txtTotalAmount);

        rvMyBalance = (RecyclerView) findViewById(R.id.rvMyBalance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText(getString(R.string.my_balance));

        txtTotalAmount.setText(getString(R.string.rupee) + " " + 452);

        mAdapter = new MyBalanceAdapter(mContext,myBalanceList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvMyBalance.setLayoutManager(mLayoutManager);
        rvMyBalance.setItemAnimator(new DefaultItemAnimator());
        rvMyBalance.setAdapter(mAdapter);

        prepareMovieData();

    }

    private void prepareMovieData() {
        MyBalanceModel movie = new MyBalanceModel("Motanad Daily Point added", "Nov, 17,2017", "452", true);
        myBalanceList.add(movie);

        movie = new MyBalanceModel("Motanad Daily Point added", "Nov, 16,2017", "452", true);
        myBalanceList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}
