package com.motanad.motanad.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.motanad.motanad.R;
import com.motanad.motanad.utilities.Constants;


/**
 * Created by Vishal on 28/07/2016.
 */

public class SharedPref {

    private static SharedPreferences mPref;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void setLogin(Context context, boolean login) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putBoolean(Constants.FLD_IS_LOGIN, login).commit();
    }

    public static void setUserId(Context context, long userId) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putLong(Constants.FLD_USER_ID, userId).commit();
    }

    public static void setProfileUpdated(Context context, boolean profile){
        mPref = (SharedPreferences) context.getSharedPreferences("TestBoard", Context.MODE_PRIVATE);
        mPref.edit().putBoolean(Constants.FLD_IS_PROFILE_UPDATED, profile).commit();
    }

    public static boolean isLogin(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean isLogin = mPref.getBoolean(Constants.FLD_IS_LOGIN, false);
        return isLogin;
    }

    public static boolean isProfileUpdated(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean isProfileUpdated = mPref.getBoolean(Constants.FLD_IS_PROFILE_UPDATED, false);
        return isProfileUpdated;
    }

    public static long getUserId(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        long userId = mPref.getLong(Constants.FLD_USER_ID, 0);
        return userId;
    }

    public static int getMemberId(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        int memberId = mPref.getInt(Constants.FLD_MEMBER_ID, 0);
        return memberId;
    }

    public static void setMemberId(Context context, int memberId) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putInt(Constants.FLD_MEMBER_ID, memberId).commit();
    }

    public static String getUserModelJSON(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        String userModelJson = mPref.getString(Constants.FLD_USER_MODEL_JSON, "");
        return userModelJson;
    }

    public static void setUserModelJSON(Context context, String userModelJson) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putString(Constants.FLD_USER_MODEL_JSON, userModelJson).commit();
    }

    public static String getExamDataModelJSON(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences("TestBoardExamData", Context.MODE_PRIVATE);
        String examDataModelJson = mPref.getString(Constants.FLD_EXAM_DATA_MODEL_JSON, "");
        return examDataModelJson;
    }

    public static void setExamDataModelJSON(Context context, String examDataModelJson) {
        mPref = (SharedPreferences) context.getSharedPreferences("TestBoardExamData", Context.MODE_PRIVATE);
        mPref.edit().putString(Constants.FLD_EXAM_DATA_MODEL_JSON, examDataModelJson).commit();
    }

    public static void setLoginUserToken(Context context, String token) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putString(Constants.FLD_USER_TOKEN, token).commit();
    }

    public static String getLoginUserToken() {
        mPref = (SharedPreferences) mContext.getSharedPreferences(mContext.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        String userToken = mPref.getString(Constants.FLD_USER_TOKEN, "");
        return userToken;
    }

    public static void setScreenW(Context context, int screenW) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putInt(Constants.FLD_SCREEN_WIDTH, screenW).commit();
    }

    public static int getScreenW(Context context) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        return mPref.getInt(Constants.FLD_SCREEN_WIDTH, 0);
    }

    public static void setDeviceToken(Context context, String deviceToken) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putString(Constants.FLD_DEVICE_TOKEN, deviceToken).commit();
    }

    public static String getDeviceToken() {
        mPref = (SharedPreferences) mContext.getSharedPreferences(mContext.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        String deviceToken = mPref.getString(Constants.FLD_DEVICE_TOKEN, "");
        return deviceToken;
    }


    public static void setBuildVersion(Context context, String deviceToken) {
        mPref = (SharedPreferences) context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        mPref.edit().putString(Constants.FLD_BUILD_VERSION, deviceToken).commit();
    }

    public static String getBuildVersion() {
        mPref = (SharedPreferences) mContext.getSharedPreferences(mContext.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        String deviceToken = mPref.getString(Constants.FLD_BUILD_VERSION, "");
        return deviceToken;
    }
}
