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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.R;
import com.motanad.motanad.loopjServcice.CmdParams;
import com.motanad.motanad.loopjServcice.JsonDeserializer;
import com.motanad.motanad.loopjServcice.NetworkManager;
import com.motanad.motanad.modelTemp.RegisterTemp;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.utilities.AppUrls;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;

import java.util.Calendar;

public class RegisterActivity extends BaseAppCompactActivity implements View.OnClickListener, NetworkManager.onCallback {

    private Toolbar toolbar;

    private TextView activityTitle;

    private TextView txtDOB;
    private TextView txtTermsAndCond;
    private TextView txtAlredyAccount;
    private TextView txtCountryCode;

    private EditText eTextUserNameReg;
    private EditText eTextFNameReg;
    private EditText eTextLNameReg;
    private EditText eTextMobileReg;
    private EditText eTextPasswordReg;
    private EditText eTextConfirmPassReg;
    private EditText eTextReferralCode;

    private Button btnSignUP;

    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;

    private CheckBox cbTerms;

    private Context mContext;
    private int mYear;
    private int mMonth;
    private int mDay;

    private String name = "";
    private String mobile = "";
    private String password = "";
    private String dob = "";
    private String gender = "";
    private String refferalCode = "";
    private String userName = "";
    private String fName = "";
    private String lName = "";
    private String confirmPass = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mContext = RegisterActivity.this;

        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        activityTitle = (TextView) toolbar.findViewById(R.id.activityTitle);

        txtDOB = (TextView) findViewById(R.id.txtDOB);
        txtTermsAndCond = (TextView) findViewById(R.id.txtTermsAndCond);
        txtAlredyAccount = (TextView) findViewById(R.id.txtAlredyAccount);
        txtCountryCode = (TextView) findViewById(R.id.txtCountryCode);

        eTextUserNameReg = (EditText) findViewById(R.id.eTextUserNameReg);
        eTextFNameReg = (EditText) findViewById(R.id.eTextFNameReg);
        eTextLNameReg = (EditText) findViewById(R.id.eTextLNameReg);
        eTextMobileReg = (EditText) findViewById(R.id.eTextMobileReg);
        eTextPasswordReg = (EditText) findViewById(R.id.eTextPasswordReg);
        eTextConfirmPassReg = (EditText) findViewById(R.id.eTextConfirmPassReg);
        eTextReferralCode = (EditText) findViewById(R.id.eTextReferralCode);

        btnSignUP = (Button) findViewById(R.id.btnSignUP);

        rbtnMale = (RadioButton) findViewById(R.id.rbtnMale);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);

        cbTerms = (CheckBox) findViewById(R.id.cbTerms);

        txtDOB.setOnClickListener(this);
        btnSignUP.setOnClickListener(this);

        activityTitle.setText(getString(R.string.sign_up));

        customTextViewForTerms();

        customTextviewForLogin();

    }

    private void customTextviewForLogin() {
        SpannableString ss = new SpannableString(getResources().getString(R.string.already_have_an_account));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent in = new Intent(mContext, LoginActivity.class);
                in.putExtra("flag", 0);
                startActivity(in);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 25, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 25, 30, 0);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 25, 30, 0);

        txtAlredyAccount.setText(ss);
        txtAlredyAccount.setMovementMethod(LinkMovementMethod.getInstance());
        txtAlredyAccount.setHighlightColor(Color.TRANSPARENT);
    }

    private void customTextViewForTerms() {
        SpannableString ss = new SpannableString(getResources().getString(R.string.i_agree_with_terms) +" ");
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
        ss.setSpan(clickableSpan, 13, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 13, 31, 0);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 13, 31, 0);

        SpannableString ss1 = new SpannableString(getResources().getString(R.string.privacy_policy));
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent in = new Intent(mContext, LoginActivity.class);
                in.putExtra("flag", 1);
                startActivity(in);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss1.setSpan(clickableSpan1, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, 14, 0);
        ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, 14, 0);

        txtTermsAndCond.setText(ss);
        txtTermsAndCond.append(ss1);
        txtTermsAndCond.setMovementMethod(LinkMovementMethod.getInstance());
        txtTermsAndCond.setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUP){
            registerProcess();
        } else if (view.getId() == R.id.txtDOB){
            setDOB();
        }
    }

    private void setDOB() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDOB.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void registerProcess() {
        userName = eTextUserNameReg.getText().toString();
        email = eTextUserNameReg.getText().toString();
        fName = eTextFNameReg.getText().toString();
        lName = eTextLNameReg.getText().toString();
        mobile = eTextMobileReg.getText().toString();
        password = eTextPasswordReg.getText().toString();
        confirmPass = eTextConfirmPassReg.getText().toString();
        refferalCode = eTextReferralCode.getText().toString();

//        dob = txtDOB.getText().toString();
//
//        if (rbtnMale.isChecked()){
//            gender = "male";
//        } else {
//            gender = "female";
//        }

        if (cbTerms.isChecked()){

            if (Util.isValidName(this, fName) && Util.isValidMobileNumber(this, mobile) && Util.isValidEmail(this, email)
                    && Util.isValidPassword(this, password) && Util.isPasswordMatch(this, password, confirmPass) && Util.isValidRefferalCode(this, refferalCode)){
                callApi();
            }

        } else {
            Toast.makeText(mContext, getString(R.string.check_terms_and_cond_first), Toast.LENGTH_SHORT).show();
        }
    }

    private void callApi() {
        String apiTitle = "Loading...";
        RequestParams params = CmdParams.CreateRegisterCmd(userName, fName, lName, mobile, password, refferalCode, "4512", "4512", email);
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, Constants.VAL_POST, AppUrls.USER_REGISTER_URL + "/" +Constants.TOKEN_ID, params, apiTitle, this, true, 0);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        RegisterTemp registerTemp = JsonDeserializer.deserializeJson(response, RegisterTemp.class);
        if (registerTemp.status == 1){
            SharedPref.setUserId(mContext, registerTemp.user_id);
            SharedPref.setLogin(mContext, true);

            Util.startActivityWithFinish(mContext, Dashboard.class);
        }
        else {
            Util.showDefaultAlert(mContext, registerTemp.message, null);
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Toast.makeText(mContext, "Failure", Toast.LENGTH_SHORT).show();
    }
}
