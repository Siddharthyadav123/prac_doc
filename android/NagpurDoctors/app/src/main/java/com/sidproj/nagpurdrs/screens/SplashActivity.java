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
                finish();
                Intent i = new Intent(SplashActivity.this, LoginFlowActivity.class);
                startActivity(i);
            }
        }, 2000);

    }


}
