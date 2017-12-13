package com.motanad.motanad.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.motanad.motanad.R;
import com.motanad.motanad.fragments.HomeFrag;
import com.motanad.motanad.fragments.NavMenuFrag;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends BaseAppCompactActivity implements NavMenuFrag.FragmentDrawerListener, View.OnClickListener{

    public TextView title;

    private Toolbar toolbar;

    private NavMenuFrag navMenuFrag;

    boolean doubleBackToExitPressedOnce = false;

    private Fragment fragment = new HomeFrag();
    private Fragment fragmentHome = new HomeFrag();
    private Context mContext;





    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        mContext = Dashboard.this;
        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        title = (TextView)toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        navMenuFrag = (NavMenuFrag) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        navMenuFrag.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);
        navMenuFrag.setDrawerListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        loadHomeFragment();
        displayView(getResources().getString(R.string.Home));
    }

    private void displayView(String menuNmae) {
        fragment = null;
        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Home))) {

            if (fragmentHome == null){
                fragmentHome = new HomeFrag();
            }
            fragment = fragmentHome;
            loadHomeFragment();
        }else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Sign_Out))) {

            dialog=  Util.retryAlertDialog(this, getResources().getString(R.string.app_name), getResources().getString(R.string.Are_you_sure_to_logout), getResources().getString(R.string.Cancel), getResources().getString(R.string.Yes), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        logout(true);
                    Util.logout(Dashboard.this);
                    dialog.dismiss();
                }
            });


        } else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.ChangePassword))){
            Util.startActivity(mContext, ChangePassword.class);
        } else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.my_balance))){
            Util.startActivity(mContext, MyBalanceActivity.class);
        } else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Profile))){
            Util.startActivity(mContext, ProfileAcitvity.class);
        } else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.InviteFriends))){
            Util.startActivity(mContext, ReferActivity.class);
        } else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.AdvertisWithUs))){
            Util.startActivity(mContext, AddActivity.class);
        }

    }

    private void loadHomeFragment() {
        if(fragment!=null) {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                    android.R.anim.fade_out);
////            fragmentTransaction.replace(R.id.fmContainer, fragment, CURRENT_TAG);
//            fragmentTransaction.replace(R.id.fmContainer, fragment).addToBackStack(null).commit();
////            fragmentTransaction.commitAllowingStateLoss();

            Util.replaceFrg(this, fragment, true, Constants.DEFAULT_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment frg = getSupportFragmentManager().findFragmentById(R.id.fmContainer);
        frg.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        backCountToExit();
    }

    private void backCountToExit() {

        FragmentManager fm = this.getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 1 ){

            fm.popBackStack();


        } else {

            if (doubleBackToExitPressedOnce) {
                finish();
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.Please_BACK_again_to_exit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);


        }
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
    }

    @Override
    public void onDrawerItemSelected(View view, String menuName) {
        displayView(menuName);
    }
}
