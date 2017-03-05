package com.sidproj.nagpurdrs.dailogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.sidproj.nagpurdrs.R;

/**
 * Created by akshay phadnis on 05-Mar-17.
 */

public class AboutUsDialog extends AbstractBaseDialog {
    public AboutUsDialog(Context context) {
        super(context);
    }

    @Override
    public View setViews(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.about_us_layout, null);
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void registerEvnts(View view) {

    }

    @Override
    public void setInfoInUI(View view) {

    }

    @Override
    public void onClickEvent(View actionView) {

    }
}

