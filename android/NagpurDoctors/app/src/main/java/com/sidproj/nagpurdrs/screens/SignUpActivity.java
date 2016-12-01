package com.sidproj.nagpurdrs.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.sidproj.nagpurdrs.R;

public class SignUpActivity extends BaseActivity {

    private EditText userNameEditText;
    private EditText pwdEditText;
    private EditText confirmPwdEditText;
    private EditText fullNameEditText;
    private EditText addressEditText;
    private EditText mobnoEditText;
    private EditText dobTextView;
    private boolean isMale = false;

    private LinearLayout bottomBarLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupActionBar(true, "SignUp (Patient)");
        hideNotificationBtn();
        initViews();
        registerEvents();
    }

    private void initViews() {
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        pwdEditText = (EditText) findViewById(R.id.pwdEditText);
        confirmPwdEditText = (EditText) findViewById(R.id.confirmPwdEditText);
        fullNameEditText = (EditText) findViewById(R.id.fullNameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        mobnoEditText = (EditText) findViewById(R.id.mobnoEditText);
        dobTextView = (EditText) findViewById(R.id.dobTextView);
        bottomBarLinearLayout = (LinearLayout) findViewById(R.id.bottomBarLinearLayout);
    }

    private boolean isValid() {
        if (userNameEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter user name");
            return false;
        } else if (pwdEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter password");
            return false;
        } else if (confirmPwdEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter user name");
        }

        return true;
    }

    private void registerEvents() {
        bottomBarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
