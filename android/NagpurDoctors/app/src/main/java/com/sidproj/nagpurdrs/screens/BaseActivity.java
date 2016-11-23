package com.sidproj.nagpurdrs.screens;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;


/**
 * Created by siddharthyadav on 06/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private TextView logoNameTextView;
    protected Toolbar toolbar = null;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

}
