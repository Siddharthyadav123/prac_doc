package com.sidproj.nagpurdrs.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.volly.APIHandler;

import org.json.JSONObject;

public class LoginFlowActivity extends BaseActivity {

    private ImageView logoImageView;

    //login option views
    private LinearLayout loginOptionBtnContainer;
    private Button drLoginBtn;
    private Button patientLoginBtn;
    private TextView signUpTextView;


    //login form views
    private LinearLayout loginFormContainer;
    private Button backBtn;
    private Button loginBtn;
    private EditText userNameEditText;
    private EditText pwdEditText;

    boolean isDoctorLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_flow);
        initViews();
        registerEvents();
    }

    private void initViews() {
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        drLoginBtn = (Button) findViewById(R.id.drLoginBtn);
        patientLoginBtn = (Button) findViewById(R.id.patientLoginBtn);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);

        loginOptionBtnContainer = (LinearLayout) findViewById(R.id.loginOptionBtnContainer);
        loginFormContainer = (LinearLayout) findViewById(R.id.loginFormContainer);

        backBtn = (Button) findViewById(R.id.backBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        pwdEditText = (EditText) findViewById(R.id.pwdEditText);
    }

    private void registerEvents() {
        drLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDoctorLogin = true;
                swichFormsWithAnimation(loginFormContainer, loginOptionBtnContainer);
            }
        });

        patientLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDoctorLogin = false;
                swichFormsWithAnimation(loginFormContainer, loginOptionBtnContainer);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                swichFormsWithAnimation(loginOptionBtnContainer, loginFormContainer);
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginFlowActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    requestSignIn();
                }
            }
        });
    }

    private void requestSignIn() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uname", userNameEditText.getText().toString().trim());
            jsonObject.put("pwd", pwdEditText.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }


        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_USER_SIGNIN, Request.Method.POST, URLConstants.URL_POST_USER_SIGNIN
                , true, "Please wait while signing in...", jsonObject.toString(), false);
        apiHandler.requestAPI();
    }

    private boolean isValid() {
        if (userNameEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter user name");
            return false;
        } else if (pwdEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter password");
            return false;
        }

        return true;
    }

    private void swichFormsWithAnimation(final LinearLayout layoutToShow, final LinearLayout layoutToHide) {
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutToHide.setVisibility(View.GONE);
                layoutToShow.setVisibility(View.VISIBLE);
                Animation fadeIn = AnimationUtils.loadAnimation(LoginFlowActivity.this, R.anim.fade_in);
                layoutToShow.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutToHide.startAnimation(fadeOut);
    }


    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            Toast.makeText(LoginFlowActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();

            Gson gson = new Gson();
            UserProfileDo userProfileDo = gson.fromJson(response, UserProfileDo.class);

            Intent i = new Intent(LoginFlowActivity.this, HomeScreenActivity.class);
            i.putExtra("user_details", userProfileDo);
            startActivity(i);

            finish();
        }
    }
}
