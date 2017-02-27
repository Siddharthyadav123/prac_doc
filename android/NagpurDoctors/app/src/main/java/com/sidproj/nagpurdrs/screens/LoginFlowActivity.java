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
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.dailogs.DoctorSignupDetailDailog;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.DoctorLoginProfileDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APIHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.RealmObject;

public class LoginFlowActivity extends BaseActivity {

    private ImageView logoImageView;

    //login option views
    private LinearLayout loginOptionBtnContainer;
    private Button drLoginBtn;
    private Button patientLoginBtn;
    private TextView signUpTextView;
    private TextView signUpDrTextView;


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

    @Override
    protected void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements) {

    }

    private void initViews() {
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        drLoginBtn = (Button) findViewById(R.id.drLoginBtn);
        patientLoginBtn = (Button) findViewById(R.id.patientLoginBtn);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpDrTextView = (TextView) findViewById(R.id.signUpDrTextView);

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

        signUpDrTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorSignupDetailDailog doctorSignupDetailDailog = new DoctorSignupDetailDailog(LoginFlowActivity.this);
                doctorSignupDetailDailog.show();
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


        if (!isDoctorLogin) {
            APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_USER_SIGNIN, Request.Method.POST, URLConstants.URL_POST_USER_SIGNIN
                    , true, "Please wait while signing in...", jsonObject.toString());
            apiHandler.requestAPI();
        } else {
            APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_DR_SIGNIN, Request.Method.POST,
                    URLConstants.URL_POST_DR_SIGNIN, true, "Please wait while signing in...", jsonObject.toString());
            apiHandler.requestAPI();
        }

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
    public void onBackPressed() {
        if (loginFormContainer.getVisibility() == View.VISIBLE) {
            swichFormsWithAnimation(loginOptionBtnContainer, loginFormContainer);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            Toast.makeText(LoginFlowActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();

            Gson gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();


            if (!isDoctorLogin) {
                UserProfileDo userProfileDo = gson.fromJson(response, UserProfileDo.class);
                LocalModel.getInstance().saveUserProfile(this, userProfileDo);
                Intent i = new Intent(LoginFlowActivity.this, HomeScreenActivity.class);
                startActivity(i);
            } else {
                DoctorLoginProfileDo doctorLoginProfileDo = gson.fromJson(response, DoctorLoginProfileDo.class);
                LocalModel.getInstance().saveDoctorProfile(this, doctorLoginProfileDo);
                Intent i = new Intent(LoginFlowActivity.this, DrHomeScreenActivity.class);
                startActivity(i);
            }
            finish();
        }
    }
}
