package com.sidproj.nagpurdrs.screens;

import android.os.Bundle;

import com.sidproj.nagpurdrs.R;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupActionBar(true, "SignUp (Patient)");
    }
}
