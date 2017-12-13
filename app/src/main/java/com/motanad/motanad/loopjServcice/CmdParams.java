package com.motanad.motanad.loopjServcice;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.utilities.Constants;

import org.json.JSONObject;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class CmdParams {

//    public static JSONObject params = null;
    public static RequestParams params = null;

    public static RequestParams createLoginCmd(String... param){
        params = new RequestParams();

        params.put(Constants.FLD_USER_NAME, param[0]);
        params.put(Constants.FLD_PASSWORD, param[1]);
        return params;
    }

    public static RequestParams createChangePassCmd(String... param){
        params = new RequestParams();

        params.put(Constants.FLD_USER_ID, param[0]);
        params.put(Constants.FLD_OLD_PASSWORD, param[1]);
        params.put(Constants.FLD_NEW_PASSWORD, param[2]);
        return params;
    }


    public static RequestParams CreateRegisterCmd(String... param) {
        params = new RequestParams();       //    cmd params

        params.put(Constants.FLD_USER_NAME, param[0]);
        params.put(Constants.FLD_FIRST_NAME, param[1]);
        params.put(Constants.FLD_LAST_NAME, param[2]);
        params.put(Constants.FLD_MOBILE, param[3]);
        params.put(Constants.FLD_PASSWORD, param[4]);
        params.put(Constants.FLD_BY_SPONSOR_CODE, param[5]);
        params.put(Constants.FLD_LAST_LOGIN, param[6]);
        params.put(Constants.FLD_TOTAL_INCOME, param[7]);
        params.put(Constants.FLD_EMAIL, param[8]);

        return params;
    }

    public static RequestParams CreateUserId(String... param) {
        params = new RequestParams();       //    cmd params

        params.put(Constants.FLD_USER_ID, param[0]);

        return params;
    }

    public static RequestParams registerFB(String f_name, String l_name, String email) {

        params = new RequestParams();       //    cmd params

        params.put("email", email);
        params.put("f_name", f_name);
        params.put("l_name", l_name);

        return params;
    }


    public static RequestParams getParams(String email, String pass) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", pass);
        return params;
    }

    public static RequestParams getParamsForgot(String email) {
        RequestParams params = new RequestParams();
        params.put("email", email);

        return params;
    }

    public static RequestParams getParamsContactUs(String name, String email, String mobile, String comment) {
        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("comment", comment);
        return params;
    }
}
