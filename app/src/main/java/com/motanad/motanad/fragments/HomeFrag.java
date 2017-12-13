package com.motanad.motanad.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motanad.motanad.R;
import com.motanad.motanad.activity.Dashboard;
import com.motanad.motanad.utilities.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {

    private View view;

    private Context mContext;

    private TabLayout tabLayout;

    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home, container, false);

        mContext = getActivity();
        initialize();

        return view;
    }

    private void initialize() {

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();

    }

    @Override
    public void onResume() {

        ((Dashboard)  getActivity()).title.setText(getActivity().getResources().getString(R.string.app_name));
        super.onResume();
    }

    private void setTabIcons() {
        TextView tabHome = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabHome.setText("Home");
       // tabHome.setCompoundDrawablesWithIntrinsicBounds(0, Util.setTintColorIcon(mContext, R.mipmap.home), 0, 0);
        tabHome.setCompoundDrawablesWithIntrinsicBounds(null,Util.setTintColorIcon(mContext, R.mipmap.home),null,null);

        tabLayout.getTabAt(0).setCustomView(tabHome);

        TextView tabNetwork = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabNetwork.setText("Network");
        tabNetwork.setCompoundDrawablesWithIntrinsicBounds(null, Util.setTintColorIcon(mContext, R.mipmap.network), null, null);
        tabLayout.getTabAt(1).setCustomView(tabNetwork);

        TextView tabDelas = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabDelas.setText("Deals");
        tabDelas.setCompoundDrawablesWithIntrinsicBounds(null, Util.setTintColorIcon(mContext, R.mipmap.deals), null, null);
        tabLayout.getTabAt(2).setCustomView(tabDelas);

        TextView tabTube = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTube.setText("Tube");
        tabTube.setCompoundDrawablesWithIntrinsicBounds(null, Util.setTintColorIcon(mContext, R.mipmap.tube), null, null);
        tabLayout.getTabAt(3).setCustomView(tabTube);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "Home");
        adapter.addFragment(new NetworkFrag(), "Network");
        adapter.addFragment(new DealFrag(), "Delas");
        adapter.addFragment(new TubeFrag(), "tube");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
