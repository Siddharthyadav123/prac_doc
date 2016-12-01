package com.sidproj.nagpurdrs.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.location.LocationModel;


/**
 * Created by siddharth on 9/6/2016.
 */
public class GpsLocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                startCheckingForLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startCheckingForLocation() {
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    LocationModel locationModel = MyApplication.getInstance().locationModel;
                    //fetching location
                    locationModel.initialize();
                    if (locationModel.getLongitude() == 0.0 && locationModel.getLatitude() == 0.0) {
                        startCheckingForLocation();
                    }
                }
            }, 1200);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
