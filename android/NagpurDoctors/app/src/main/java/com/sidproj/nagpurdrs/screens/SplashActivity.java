package com.sidproj.nagpurdrs.screens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.entities.DoctorLoginProfileDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.model.LocalModel;

public class SplashActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView logoNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        animateImage();
    }

    private void initViews() {
        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        logoNameTextView = (TextView) findViewById(R.id.logoNameTextView);
    }

    private void animateImage() {
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_bottom);
        logoImageView.startAnimation(logoAnim);

        //finishing screen after 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserProfileDo userProfileDo = LocalModel.getInstance().loadUserProfileIfExist(SplashActivity.this);
                DoctorLoginProfileDo doctorLoginProfileDo = LocalModel.getInstance().loadDrProfileIfExist(SplashActivity.this);
                finish();
                if (userProfileDo != null) {
                    Intent i = new Intent(SplashActivity.this, HomeScreenActivity.class);
                    startActivity(i);
                } else if (doctorLoginProfileDo != null) {
                    Intent i = new Intent(SplashActivity.this, DrHomeScreenActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginFlowActivity.class);
                    startActivity(i);
                }

            }
        }, 2000);

    }


}
