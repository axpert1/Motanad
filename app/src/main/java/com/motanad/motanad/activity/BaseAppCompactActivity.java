package com.motanad.motanad.activity;

import android.support.v7.app.AppCompatActivity;

import com.motanad.motanad.models.SharedPref;


/**
 * Created by a on 5/30/2017.
 */
public class BaseAppCompactActivity extends AppCompatActivity {
public static BaseAppCompactActivity baseAppCompactActivity;
    @Override
    protected void onResume() {
        super.onResume();
        baseAppCompactActivity=this;
        SharedPref.init(this);
    }
}
