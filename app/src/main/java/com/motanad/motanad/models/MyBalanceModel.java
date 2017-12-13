package com.motanad.motanad.models;

/**
 * Created by Admin on 9-12-17.
 */

public class MyBalanceModel {

    public String title;
    public String date;
    public String price;

    public boolean isAdded;

    public MyBalanceModel(String title, String date, String price, boolean isAdded){
        this.title = title;
        this.date = date;
        this.price = price;
        this.isAdded = isAdded;
    }
}
