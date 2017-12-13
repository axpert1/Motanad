package com.motanad.motanad.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.motanad.motanad.R;

public class ReferActivity extends BaseAppCompactActivity {

    private Toolbar toolbar;

    private TextView title;
    private TextView txtRefferalCode;

    private Button btnRefer;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);

        mContext = ReferActivity.this;
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        txtRefferalCode = (TextView) findViewById(R.id.txtRefferalCode);

        btnRefer = (Button) findViewById(R.id.btnRefer);

        btnRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                referProcess();
            }
        });

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

        title.setText(getString(R.string.InviteFriends));
    }

    private void referProcess() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "MotanAd");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "For Download MotanAd App clicke here://androidsolved.wordpress.com/ ");
        startActivity(Intent.createChooser(sharingIntent, "Refer via"));
    }
}
