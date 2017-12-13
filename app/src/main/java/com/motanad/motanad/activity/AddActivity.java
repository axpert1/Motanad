package com.motanad.motanad.activity;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.motanad.motanad.R;
import com.motanad.motanad.utilities.Util;

public class AddActivity extends AppCompatActivity {

    private Context mContext;

    private Button btnAdd;

    private String TAG = AddActivity.class.getSimpleName();
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mContext = AddActivity.this;

        initialize();
    }

    private void initialize() {

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String android_id = Settings.Secure.getString(mContext.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                Util.getAdd(mContext, android_id);
            }
        });


    }

}
