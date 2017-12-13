package com.motanad.motanad.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.motanad.motanad.R;
import com.motanad.motanad.activity.LoginActivity;
import com.motanad.motanad.models.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


/**
 * Created by a on 7/2/2017.
 */

public class Util {

    private static AlertDialog retryCustomAlert;
    private static Dialog apiCallingProgressDialog;
    private static JSONObject jsonObject1;
    private static String message;

    public static void setDeviceSreenH(Activity act) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        SharedPref.setScreenW(act, width);
    }

    public static Drawable setTintColorIcon(Context context, int drawableIcon) {
        final Drawable icons = context.getResources().getDrawable(drawableIcon);
        icons.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        return icons;
    }


    //to start any activity.
    public static void startActivityWithFinish(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClass(context, class1);
        ((Activity) context).startActivity(intent);
        ((Activity) context).finish();
    }

    public static void startActivity(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setClass(context, class1);
        ((Activity) context).startActivity(intent);
    }

    public static void logout(Activity mContext) {
        SharedPref.setLogin(mContext, false);
        SharedPref.setUserModelJSON(mContext, "");
        SharedPref.setMemberId(mContext, 0);
        Util.startActivityWithFinish(mContext, LoginActivity.class);
    }

    public static void setImage(Context mContext, ImageView imageView, String imageUrl){
        Glide.with(mContext)
                .load(imageUrl)
                .priority(Priority.IMMEDIATE)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .into(imageView);
    }

    public static AlertDialog retryAlertDialog(Context mContext, String title, String msg, String firstButton, String SecondButton, View.OnClickListener secondButtonClick) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setCancelable(false);
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.retry_alert, null);
        dialogBuilder.setView(dialogView);

        TextView txtRAMsg = (TextView) dialogView.findViewById(R.id.txtRAMsg);
        TextView txtRAFirst = (TextView) dialogView.findViewById(R.id.txtRAFirst);
        TextView txtRASecond = (TextView) dialogView.findViewById(R.id.txtRASecond);
        View deviderView = (View) dialogView.findViewById(R.id.deviderView);

        txtRAMsg.setText(msg);

        if (firstButton.length() == 0) {
            txtRAFirst.setVisibility(View.GONE);
            deviderView.setVisibility(View.GONE);
        } else {
            txtRAFirst.setVisibility(View.VISIBLE);
            txtRAFirst.setText(firstButton);
        }

        if (SecondButton.length() == 0) {
            txtRASecond.setVisibility(View.GONE);
            deviderView.setVisibility(View.GONE);
        } else {
            txtRASecond.setVisibility(View.VISIBLE);
            txtRASecond.setText(SecondButton);
        }

        txtRAFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retryCustomAlert.dismiss();
            }
        });

        if(secondButtonClick==null)
        {
            secondButtonClick=new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    retryCustomAlert.dismiss();
                }
            };
        }
        txtRASecond.setOnClickListener(secondButtonClick);

        retryCustomAlert = dialogBuilder.create();
        retryCustomAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        retryCustomAlert.show();
        return retryCustomAlert;
    }

    public static void dismissRetryAlert() {
        if (retryCustomAlert != null) {
            retryCustomAlert.dismiss();
        }
    }

    public static void showDefaultAlert(final Context mContext, String msg, final Class<?> cls) {
        new android.support.v7.app.AlertDialog.Builder(mContext)
                .setTitle(mContext.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (cls != null) {
                            startActivityWithFinish(mContext, cls);
                        }
                    }
                })
                .setIcon(R.drawable.splash_logo)
                .show();
    }

    public static boolean isValidUserName(Activity act, String fName) {
        if (fName.trim().length() <= 0) {
//            Toast.makeText(act.getApplicationContext(),act.getResources().getString(R.string.error_user_name_blank),Toast.LENGTH_SHORT).show();
//            showDefaultAlert(act, act.getResources().getString(R.string.error_user_name_blank), null);
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getResources().getString(R.string.error_user_user_name_blank), act.getResources().getString(R.string.Ok), "", null);
        }
//       else if (fName.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
//            showDefaultAlert(act, act.getString(R.string.name_error_msg),null);
//        }
        else if (checkStringsContainsOnlySpecialChar(fName)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.only_special_characters_not_allowed), act.getResources().getString(R.string.Ok), "", null);
        } else if (isNumeric(fName)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.only_numbers_not_allowed), act.getResources().getString(R.string.Ok), "", null);
        } else {
            return true;
        }
        return false;
    }

    public static boolean isValidName(Activity act, String fName) {
        if (fName.trim().length() <= 0) {
//            Toast.makeText(act.getApplicationContext(),act.getResources().getString(R.string.error_user_name_blank),Toast.LENGTH_SHORT).show();
//            showDefaultAlert(act, act.getResources().getString(R.string.error_user_name_blank), null);
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getResources().getString(R.string.error_user_name_blank), act.getResources().getString(R.string.Ok), "", null);
        }
//       else if (fName.trim().length() < Constants.MINIMUMLENGTHOFNAME) {
//            showDefaultAlert(act, act.getString(R.string.name_error_msg),null);
//        }
        else if (checkStringsContainsOnlySpecialChar(fName)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.only_special_characters_not_allowed), act.getResources().getString(R.string.Ok), "", null);
        } else if (isNumeric(fName)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.only_numbers_not_allowed), act.getResources().getString(R.string.Ok), "", null);
        } else {
            return true;
        }
        return false;
    }

    public static boolean isValidRefferalCode(Activity act, String refferal) {
        if (refferal.trim().length() <= 0) {
//            Toast.makeText(act.getApplicationContext(),act.getResources().getString(R.string.error_user_name_blank),Toast.LENGTH_SHORT).show();
//            showDefaultAlert(act, act.getResources().getString(R.string.error_user_name_blank), null);
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getResources().getString(R.string.error_refferal_code_blank), act.getResources().getString(R.string.Ok), "", null);
        }
       else {
            return true;
        }
        return false;
    }

    public static boolean isValidDOB(Activity act, String dob){
        if (dob.trim().length() <= 0) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getResources().getString(R.string.error_dob_blank), act.getResources().getString(R.string.Ok), "", null);
        }
        else {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(Activity act, String email) {
        boolean b = true;
        if (TextUtils.isEmpty(email)) {
            b = false;
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_email_blank), act.getResources().getString(R.string.Ok), "", null);
        } else {
            b = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            if (!b) {
                retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.enter_valid_email), act.getResources().getString(R.string.Ok), "", null);
            }
        }
        return b;
    }
//
    public static boolean isValidMobileNumber(Activity act, String phone) {
        boolean b = true;
        if (TextUtils.isEmpty(phone)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_mobile_number_blank), act.getResources().getString(R.string.Ok), "", null);
            b = false;
        } else if (!Pattern.matches("[789]{1}[0-9]{9}", phone)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_mobile_number_wrong), act.getResources().getString(R.string.Ok), "", null);
            b = false;
        }
        return b;
    }
//
    public static boolean isValidPassword(Activity act, String pass) {
        boolean b = true;
        if (TextUtils.isEmpty(pass)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_password_blank), act.getResources().getString(R.string.Ok), "", null);
            b = false;
        } else if (!Pattern.matches("[^\\s]{6,15}", pass)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_password_pattern), act.getResources().getString(R.string.Ok), "", null);
            b = false;
        }
        return b;
    }
//
    public static boolean isPasswordMatch(Activity act, String pass, String cPass) {
        boolean b = true;
        if (!cPass.equals(pass)) {
            retryAlertDialog(act, act.getResources().getString(R.string.app_name), act.getString(R.string.error_password_match), act.getResources().getString(R.string.Ok), "", null);
            b = false;
        }
        return b;
    }

    public static boolean checkStringsContainsOnlySpecialChar(String inputString) {
        boolean found = false;
        try {
            String splChrs = "/^[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$/";
            found = inputString.matches("[" + splChrs + "]+");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public static boolean isNumeric(String str) {
        try {
            long d = Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void progressDialog(Context mContext, String title) {
        if (apiCallingProgressDialog == null) {

            apiCallingProgressDialog = new Dialog(mContext, android.R.style.Theme_Translucent);
            apiCallingProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            apiCallingProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            apiCallingProgressDialog.setContentView(R.layout.progress_alert);

//            TextView txtProgressMsg = (TextView) apiCallingProgressDialog.findViewById(R.id.txtProgressMsg);
//            txtProgressMsg.setText(title);

            ProgressBar progressBar = (ProgressBar) apiCallingProgressDialog.findViewById(R.id.progressBar);
            progressBar.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

            apiCallingProgressDialog.setCancelable(false);
            apiCallingProgressDialog.show();
        }
    }

    public static void dismissProgressDialog() {
        if (apiCallingProgressDialog != null) {
            apiCallingProgressDialog.dismiss();
            apiCallingProgressDialog = null;
        }
    }

//    public static void makePayment(Context mContext, okhttp3.Callback callback1, String test_id, String rewards_redeem, String total_pay_payment, String payment_referance_no, String registration_id) {
//
//        JSONObject params = CmdFactory.createMakePaymentCmd(test_id, rewards_redeem, total_pay_payment, payment_referance_no, registration_id);
//        NetworkManager.requestForAPI(mContext, callback1, Constants.VAL_POST, AppUrls.STUDENT_MAKE_PAYMENT, params.toString(), true);
//    }
//
//    public static JSONObject getObjectFromResponse(Response response) {
//        jsonObject1 = null;
//
//        try {
//            String strResponse = response.body().string();
//            if (strResponse != null && !strResponse.isEmpty()) {
//
//                jsonObject1 = new JSONObject(strResponse);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return jsonObject1;
//    }

//    public static void manageFailure(final Activity activity, final IOException e, final View v, final View.OnClickListener retryClick) {
//
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Util.dismissProgressDialog();
//                if (v != null)
//                    v.setEnabled(true);
//                if (retryClick != null) {
//                    Util.retryAlertDialog(activity, activity.getResources().getString(R.string.app_name), activity.getResources().getString(R.string.Server_not_responding), activity.getResources().getString(R.string.No), activity.getResources().getString(R.string.Retry), retryClick);
//                }
//                else
//                    Util.showDefaultAlert(activity, "server not working. please try later.", null);
//            }
//        });
//
//
//    }


    public static boolean isValidResponse(JSONObject jsonObject) {
        boolean b = false;
        try {
            if (jsonObject != null && jsonObject.getBoolean(Constants.FLD_STATUS)) {
                b = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }

    //parse the response coming from server using gson library.
    public static Object getJsonToClassObject(String jsonStr, Class<?> classs) {
        try {
            Gson gson = new GsonBuilder().create();
            return (Object) gson.fromJson(jsonStr, classs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void replaceFrg(FragmentActivity ctx, Fragment frag, boolean addToBackStack, int resId) {
        FragmentManager fm = ctx.getSupportFragmentManager();
        int addedFrgCount = fm.getBackStackEntryCount();
        FragmentTransaction ft = fm
                .beginTransaction();
        if (resId == Constants.DEFAULT_ID) {
            resId = R.id.fmContainer;
        }
        ft.replace(resId, frag, frag.getClass().getName());
        if (addToBackStack)
            ft.addToBackStack(frag.getClass().getName());
        ft.commit();
    }

//    public static void showMessageFromResponse(final Activity acti, Response response, JSONObject jsonObject) {
//        message = acti.getResources().getString(R.string.Server_not_responding);
//
//        final String status = response.header(Constants.FLD_STATUS);
//        try {
//            if (response != null) {
////                JSONObject   jsonObject=  Util.getObjectFromResponse(response);
//                if (jsonObject != null && jsonObject.has(Constants.FLD_RESPONSE_MESSAGE) && !jsonObject.isNull(Constants.FLD_RESPONSE_MESSAGE)) {
//                    message = jsonObject.getString(Constants.FLD_RESPONSE_MESSAGE);
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if (!TextUtils.isEmpty(status)) {
//
//            if (status.equalsIgnoreCase(Constants.STATUS_FAILURE)) {
//                message = Constants.Error_500;
//
//            } else if (status.equalsIgnoreCase(Constants.STATUS_FORBIDDEN)) {
//                message = Constants.Error_403;
//            } else if (status.equalsIgnoreCase(Constants.STATUS_UNAUTHORIZED)) {
////                                message= Constants.Error_401;
//            } else if (status.equalsIgnoreCase(Constants.STATUS_NOTFOUND)) {
////                                message= Constants.Error_404;
//            }
//        }
//
//        acti.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Util.showDefaultAlert(acti, message, null);
//            }
//        });
//
//
//    }

    public static Address getAddress(final Activity acti, double lat, double lon) {
        Geocoder geocoder = new Geocoder(acti, Locale.ENGLISH);

        Address address = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses != null) {
                address = addresses.get(0);
            } else {

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return address;
    }

    //to show a center toast.
    public static void showCenterToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    //will check whether device is connected to any internet connection or not.
//    public static boolean isDeviceOnline(Context context, boolean showPopUp, View.OnClickListener retryClick) {
//        boolean isNetAvailable = false;
//
//
//        try {
//            ConnectivityManager cm = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (cm.getActiveNetworkInfo() != null
//                    && cm.getActiveNetworkInfo().isAvailable()
//                    && cm.getActiveNetworkInfo().isConnected()) {
//                isNetAvailable = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            isNetAvailable = false;
//        }
//
////        try {
////            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
////            isNetAvailable= cm.getActiveNetworkInfo().isConnectedOrConnecting();
////        } catch (Exception e) {
////            isNetAvailable = false;
////            e.printStackTrace();
////        }
//        if (!isNetAvailable && showPopUp) {
//            Util.retryAlertDialog(context, context.getResources().getString(R.string.app_name), context.getResources().getString(R.string.msg_internet_connection), context.getResources().getString(R.string.No), context.getResources().getString(R.string.Retry), retryClick);
//        }
//        return isNetAvailable;
//    }

    public static String getImagePathFromResp(String imagePath, String name) {
        String imageUrl = imagePath + "/" + name;
        return imageUrl;
    }


}
