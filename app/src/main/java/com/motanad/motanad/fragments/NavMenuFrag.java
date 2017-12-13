package com.motanad.motanad.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.motanad.motanad.R;
import com.motanad.motanad.activity.Dashboard;
import com.motanad.motanad.adapters.NavMenuAdapter;
import com.motanad.motanad.custome.CircleImageView;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.models.User;
import com.motanad.motanad.utilities.Util;
import com.motanad.motanad.utilities.recycler_view_utilities.DividerItemDecorationGray;
import com.motanad.motanad.utilities.recycler_view_utilities.RecyclerItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavMenuFrag extends Fragment implements View.OnClickListener{

    private View view;

    private TextView txtUsername;

    private CircleImageView imvUserImage;

    private RecyclerView rvNavMenuList;

    private FrameLayout fLayoutProfileNav;

    private NavMenuAdapter adapter;

    private FragmentDrawerListener drawerListener;

    private DrawerLayout mDrawerLayout;
    private View containerView;

    private ActionBarDrawerToggle mDrawerToggle;

    private String[] navigationDrawerItems;

    private User user;

    private RelativeLayout relProfileSideBar;

    public void setDrawerListener(Dashboard listener) {
        this.drawerListener = listener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.nav_menu, container, false);
        initialize();
        return view;
    }

    private void initialize() {
        txtUsername = (TextView) view.findViewById(R.id.txtUsername);


        fLayoutProfileNav = (FrameLayout) view.findViewById(R.id.navProfileHeader);
        fLayoutProfileNav.setOnClickListener(this);

        imvUserImage = (CircleImageView) view.findViewById(R.id.imvUserImage);

        relProfileSideBar = (RelativeLayout) view.findViewById(R.id.relProfileSideBar);

        rvNavMenuList = (RecyclerView) view.findViewById(R.id.rvNavMenuList);

        navigationDrawerItems = getResources().getStringArray(R.array.nav_drawer_items);


        rvNavMenuList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNavMenuList.addItemDecoration(new DividerItemDecorationGray(getActivity()));
        adapter = new NavMenuAdapter(getActivity(), navigationDrawerItems);
        rvNavMenuList.setAdapter(adapter);

        rvNavMenuList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, navigationDrawerItems[position]);
                mDrawerLayout.closeDrawer(containerView);
            }
        }));


        relProfileSideBar.setOnClickListener(this);

    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
                setData();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    private void setData() {
        user = (User) Util.getJsonToClassObject(SharedPref.getUserModelJSON(getActivity()), User.class);
        if (user != null) {

//            if (user.data != null) {
//                txtUsername.setText("" + user.data.name);
//
//                String imageUrl = user.image_path + "/" + user.data.student_details_data.image;
//
//                Glide.with(getActivity())
//                        .load(imageUrl)
//                        .priority(Priority.IMMEDIATE)
//                        .error(R.mipmap.myprofile_thumb)
//                        .fallback(R.mipmap.myprofile_thumb)
//                        .into(imvUserImage);
//            }
        }
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        if (vId == R.id.relProfileSideBar) {
            drawerListener.onDrawerItemSelected(view, navigationDrawerItems[0]);
            mDrawerLayout.closeDrawer(containerView);
        } else if (vId == R.id.navProfileHeader) {

            mDrawerLayout.closeDrawer(containerView);

//            Util.replaceFrg(getActivity(), new ProfileFragment(), true, Constants.DEFAULT_ID);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, String menuName);

    }

}
