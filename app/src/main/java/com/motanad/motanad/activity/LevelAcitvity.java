package com.motanad.motanad.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.motanad.motanad.R;
import com.motanad.motanad.adapters.LevelAdapter;
import com.motanad.motanad.models.LevelModel;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.recycler_view_utilities.DividerItemDecorationGray;

import java.util.ArrayList;

public class LevelAcitvity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView title;

    private RecyclerView rvLevel;

    private LevelAdapter mAdapter;

    private ArrayList<LevelModel> levelList = new ArrayList<>();

    private Context mContext;

    private View.OnClickListener callButtonClick;

    private String level;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_acitvity);

        mContext = LevelAcitvity.this;

        Intent intent = getIntent();
        if (getIntent().hasExtra(Constants.FLD_LEVEL)) {
            level = intent.getStringExtra(Constants.FLD_LEVEL);
        }
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        rvLevel = (RecyclerView) findViewById(R.id.rvLevel);

        callButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = (int) v.getTag();
                if (isPermissionGranted()) {
                    call_action();
                }

            }
        };

        mAdapter = new LevelAdapter(mContext, levelList, callButtonClick);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLevel.setLayoutManager(mLayoutManager);
        rvLevel.setItemAnimator(new DefaultItemAnimator());
        rvLevel.addItemDecoration(new DividerItemDecorationGray(mContext));
        rvLevel.setAdapter(mAdapter);

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

        title.setText(level);

        prepareLevelList();
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void call_action() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + levelList.get(position).mobileNo));

        startActivity(intent);
    }

    private void prepareLevelList() {
        LevelModel level = new LevelModel("Hemant", "9509787937", true);
        levelList.add(level);

        level = new LevelModel("Anil", "7014630142", false);
        levelList.add(level);

        mAdapter.notifyDataSetChanged();
    }
}
