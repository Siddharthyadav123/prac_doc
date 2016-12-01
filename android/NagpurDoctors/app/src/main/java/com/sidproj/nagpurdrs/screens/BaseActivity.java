package com.sidproj.nagpurdrs.screens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;


/**
 * Created by siddharthyadav on 06/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected TextView logoNameTextView;
    protected Toolbar toolbar = null;
    protected ImageView notificationImageView;
    protected TextView notificationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void setupActionBar(boolean needBackBtn, String title) {
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logoNameTextView = (TextView) findViewById(R.id.logoNameTextView);
        logoNameTextView.setText(title);

        notificationImageView = (ImageView) findViewById(R.id.notificationImageView);
        notificationCount = (TextView) findViewById(R.id.notificationCount);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            if (needBackBtn) {
                //setting white color to toolbar button
                Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
                upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(upArrow);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


    }

    public void hideNotificationBtn() {
        notificationImageView.setVisibility(View.GONE);
        notificationCount.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    public void showInfoDailog(Context context, String bodyText) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Message");
        builder1.setMessage(bodyText);
        builder1.setCancelable(true);

        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
