package com.motanad.motanad.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.motanad.motanad.R;


/**
 * Created by Nss Solutions on 17-11-2016.
 */

public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuAdapter.DrawerViewHolder> {

    private String[] drawerMenuList;
    private Context mContext;

    public NavMenuAdapter(Context mContext, String[] drawerMenuList) {
        this.mContext = mContext;
        this.drawerMenuList = drawerMenuList;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_menu_item, parent, false);
        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        String menuNmae = drawerMenuList[position];
        holder.txtNavItemTitle.setText(menuNmae);
        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Home))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.home);
        }

        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Profile))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.profile);
        }

        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.MyBalace))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.mybalance);
        }

        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.ChangePassword))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.change_password);
        }

        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.AdvertisWithUs))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.advertise_with_us);
        }

        if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.InviteFriends))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.invite_friends);
        }

        else if (menuNmae.equalsIgnoreCase(mContext.getResources().getString(R.string.Sign_Out))) {
            holder.imvNavItemIcon.setImageResource(R.mipmap.sign_out );
        }
    }

    @Override
    public int getItemCount() {
        return drawerMenuList.length;
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {
        TextView txtNavItemTitle;
        ImageView imvNavItemIcon;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            txtNavItemTitle = (TextView) itemView.findViewById(R.id.txtNavItemTitle);
            imvNavItemIcon = (ImageView) itemView.findViewById(R.id.imvNavItemIcon);
        }
    }
}
