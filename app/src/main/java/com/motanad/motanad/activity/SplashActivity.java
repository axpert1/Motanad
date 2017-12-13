package com.motanad.motanad.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.motanad.motanad.R;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.models.User;
import com.motanad.motanad.utilities.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        user = (User) Util.getJsonToClassObject(SharedPref.getUserModelJSON(this), User.class);


        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.testboard.app",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Util.setDeviceSreenH(SplashActivity.this);
                    if (SharedPref.isLogin(SplashActivity.this)) {

//                        if (user != null) {

                            Util.startActivityWithFinish(SplashActivity.this, Dashboard.class);
//                        }

                    } else {
                        Util.startActivityWithFinish(SplashActivity.this, LoginActivity.class);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
