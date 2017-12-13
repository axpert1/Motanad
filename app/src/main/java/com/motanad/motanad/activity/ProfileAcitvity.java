package com.motanad.motanad.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.motanad.motanad.R;

public class ProfileAcitvity extends BaseAppCompactActivity implements View.OnClickListener{

    private Toolbar toolbar;

    private TextView title;
    private TextView txtSyncTime;
    private TextView txtProfileActive;
    private TextView txtProfileUName;
    private TextView txtMotanBalance;
    private TextView txtMonthlyPayout;
    private TextView txtWithdrawalAmount;
    private TextView txtActiveUsers;
    private TextView txtRefferalCode;

    private Button btnProfileRefer;

    private ImageView imvNotification;
    private ImageView imvSync;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_acitvity);

        mContext = ProfileAcitvity.this;
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        txtSyncTime = (TextView) findViewById(R.id.txtSyncTime);
        txtProfileActive = (TextView) findViewById(R.id.txtProfileActive);
        txtProfileActive = (TextView) findViewById(R.id.txtProfileUName);
        txtMotanBalance = (TextView) findViewById(R.id.txtMotanBalance);
        txtMonthlyPayout = (TextView) findViewById(R.id.txtMonthlyPayout);
        txtWithdrawalAmount = (TextView) findViewById(R.id.txtWithdrawalAmount);
        txtActiveUsers = (TextView) findViewById(R.id.txtActiveUsers);
        txtRefferalCode = (TextView) findViewById(R.id.txtRefferalCode);

        btnProfileRefer = (Button) findViewById(R.id.btnProfileRefer);

        imvNotification = (ImageView) findViewById(R.id.imvNotification);
        imvSync = (ImageView) findViewById(R.id.imvSync);

        btnProfileRefer.setOnClickListener(this);

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

        title.setText(getString(R.string.Profile));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnProfileRefer){
            shareProcess();
        }
    }

    private void shareProcess() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "MotanAd");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "For Download MotanAd App clicke here://androidsolved.wordpress.com/ ");
        startActivity(Intent.createChooser(sharingIntent, "Refer via"));
    }
}
