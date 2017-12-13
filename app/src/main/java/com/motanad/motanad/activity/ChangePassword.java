package com.motanad.motanad.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.motanad.motanad.R;
import com.motanad.motanad.loopjServcice.CmdParams;
import com.motanad.motanad.loopjServcice.JsonDeserializer;
import com.motanad.motanad.loopjServcice.NetworkManager;
import com.motanad.motanad.modelTemp.ChangePassTemp;
import com.motanad.motanad.models.SharedPref;
import com.motanad.motanad.utilities.AppUrls;
import com.motanad.motanad.utilities.Constants;
import com.motanad.motanad.utilities.Util;

import org.w3c.dom.Text;

public class ChangePassword extends BaseAppCompactActivity implements NetworkManager.onCallback {

    private Toolbar toolbar;

    private TextView title;
    private TextView eTextOldPass;
    private TextView eTextNewPass;
    private TextView eTextConfirmPassCP;

    private Button btnSubmitCP;

    private Context mContext;
    private long userId;
    private String oldPassword = "";
    private String newPassword = "";
    private String conPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        mContext = ChangePassword.this;

        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) toolbar.findViewById(R.id.activityTitle);
        setSupportActionBar(toolbar);

        eTextOldPass = (TextView) findViewById(R.id.eTextOldPass);
        eTextNewPass = (TextView) findViewById(R.id.eTextNewPass);
        eTextConfirmPassCP = (TextView) findViewById(R.id.eTextConfirmPassCP);

        btnSubmitCP = (Button) findViewById(R.id.btnSubmitCP);

        btnSubmitCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswordProcess();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText(getString(R.string.change_password));

    }

    private void changePasswordProcess() {

        userId = SharedPref.getUserId(mContext);
        oldPassword = eTextOldPass.getText().toString();
        newPassword = eTextNewPass.getText().toString();
        conPassword = eTextConfirmPassCP.getText().toString();

        if (Util.isPasswordMatch(this,newPassword,conPassword)){
            callApi();
        }


    }

    private void callApi() {
        String apiTitle = "Loading...";
        RequestParams params = CmdParams.createChangePassCmd(""+userId, oldPassword, newPassword);
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, Constants.VAL_POST, AppUrls.USER_CHANGE_PASSWORD_URL + "/" +Constants.TOKEN_ID, params, apiTitle, this, true, 0);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        ChangePassTemp changePassTemp = JsonDeserializer.deserializeJson(response, ChangePassTemp.class);

        if (changePassTemp.status == 1) {
            Toast.makeText(mContext, changePassTemp.message, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(mContext, changePassTemp.message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Toast.makeText(mContext, "Failure", Toast.LENGTH_SHORT).show();
    }
}
