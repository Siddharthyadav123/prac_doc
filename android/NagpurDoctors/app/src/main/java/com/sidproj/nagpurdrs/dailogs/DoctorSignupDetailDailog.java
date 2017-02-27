package com.sidproj.nagpurdrs.dailogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.sidproj.nagpurdrs.R;

/**
 * Created by siddharth on 2/6/2017.
 */
public class DoctorSignupDetailDailog extends AbstractBaseDialog {
    public DoctorSignupDetailDailog(Context context) {
        super(context);
    }

    @Override
    public View setViews(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.dr_signup_dialog_layout, null);
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
