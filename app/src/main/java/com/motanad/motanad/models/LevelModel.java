package com.motanad.motanad.models;

/**
 * Created by Admin on 11-12-17.
 */

public class LevelModel {

    public String name;
    public String mobileNo;
    public boolean isOnline;

    public LevelModel(String name, String mobileNo, boolean isOnline){
        this.name = name;
        this.mobileNo = mobileNo;
        this.isOnline = isOnline;
    }
}
