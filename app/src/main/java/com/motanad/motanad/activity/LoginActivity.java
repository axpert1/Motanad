package com.motanad.motanad.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.R;
import com.motanad.motanad.loopjServcice.CmdParams;
import com.motanad.motanad.loopjServcice.JsonDeserializer;
import com.motanad.motanad.loopjServcice.NetworkManager;
import com.motanad.motanad.modelTemp.LoginTemp;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.utilities.AppUrls;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;

import java.util.Calendar;

public class LoginActivity extends BaseAppCompactActivity implements View.OnClickListener, NetworkManager.onCallback {

    private Toolbar toolbar;

    private TextView activityTitle;
    private TextView txtForgotPass;
    private TextView txtDontAccount;

    private EditText eTextMobileLogin;
    private EditText eTextPasswordLogin;

    private Button btnLogin;

    private Context mContext;

    private String mobile = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = LoginActivity.this;

        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        activityTitle = (TextView) findViewById(R.id.activityTitle);

        txtForgotPass = (TextView) findViewById(R.id.txtForgotPass);
        txtDontAccount = (TextView) findViewById(R.id.txtDontAccount);

        eTextMobileLogin = (EditText) findViewById(R.id.eTextMobileLogin);
        eTextPasswordLogin = (EditText) findViewById(R.id.eTextPasswordLogin);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        activityTitle.setText(getString(R.string.login));

        customTextView();

    }

    private void customTextView() {
        SpannableString ss = new SpannableString(getResources().getString(R.string.dont_have_an_account));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent in = new Intent(mContext, RegisterActivity.class);
                in.putExtra("flag", 0);
                startActivity(in);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 23, 30, 0);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 23, 30, 0);


        txtDontAccount.setText(ss);
        txtDontAccount.setMovementMethod(LinkMovementMethod.getInstance());
        txtDontAccount.setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            loginProcess();
//            startActivity(new Intent(mContext, Dashboard.class));
        }
    }

    private void loginProcess() {
        mobile = eTextMobileLogin.getText().toString();
        password = eTextPasswordLogin.getText().toString();

        callLoginApi();
    }

    private void callLoginApi() {
        String apiTitle = "Loading...";
        RequestParams params = CmdParams.createLoginCmd(mobile, password);
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, Constants.VAL_POST, AppUrls.USER_LOGIN_URL + "/" +Constants.TOKEN_ID, params, apiTitle, this, true, 0);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        LoginTemp loginTemp = JsonDeserializer.deserializeJson(response, LoginTemp.class);

        if (loginTemp.status == 1){
            SharedPref.setLogin(mContext, true);
            SharedPref.setUserId(mContext, Long.parseLong(loginTemp.user_details.id));
            Util.startActivityWithFinish(mContext, Dashboard.class);
        }
        else {
            Util.showDefaultAlert(mContext, loginTemp.message, null);
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

        Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
    }
}
