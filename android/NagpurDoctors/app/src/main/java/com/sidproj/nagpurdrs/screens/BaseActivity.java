package com.sidproj.nagpurdrs.screens;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APICallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by siddharthyadav on 06/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements APICallback {

    protected TextView logoNameTextView;
    protected Toolbar toolbar = null;
    protected ImageView notificationImageView;
    protected TextView notificationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        MyApplication.getInstance().setCurrentActivity(null);
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

        notificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, AppointmentListActivity.class);
                startActivity(i);
            }
        });

        notificationCount.setText(LocalModel.getInstance().getAppointmentList().size() + "");
    }

    @Subscribe
    public void onNewAppointmentEvent(ArrayList<AppointmentDo> newAppointements) {
        notificationCount.setText(newAppointements.size() + "");
        onNewNotificationArrived(newAppointements);
    }

    protected abstract void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements);

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

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * -------------------------------------- Permission handling --------------------------------------
     */
    private int permissionRequestCode;
    private Object extras;
    public final int REQUEST_MARSHMELLO_PERMISSIONS = 222;
    boolean haveAllPermissions = false;

    public String[] mustPermissions =
            {
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CALL_PHONE

            };

    private boolean havePermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public boolean checkPermissions(int requestCode, String[] permission, Object extras) {
        this.permissionRequestCode = requestCode;
        this.extras = extras;
        ArrayList<String> permissionsStr = new ArrayList<>();

        for (int i = 0; i < permission.length; i++) {
            if (!havePermission(permission[i])) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission[i])) {
                    Toast.makeText(this, "Please provide this permission.", Toast.LENGTH_LONG).show();
                }
                permissionsStr.add(permission[i]);
            }
        }

        if (permissionsStr.size() > 0) {
            String[] needTopermissionArray = permissionsStr.toArray(new String[permissionsStr.size()]);
            ActivityCompat.requestPermissions(this, needTopermissionArray, requestCode);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == permissionRequestCode) {

            if (grantResults.length >= 1) {
                boolean anyDenied = false;
                for (int i = 0; i < grantResults.length; i++) {
                    // Check if the only required permission has been granted
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "PERMISSION OF " + permissions[i] + " IS GRANTED.", Toast.LENGTH_SHORT).show();
                    } else {
                        anyDenied = true;
//                        Toast.makeText(this, "PERMISSION OF " + permissions[i] + " IS DENIED.", Toast.LENGTH_SHORT).show();
                    }
                }
                if (anyDenied) {
                    haveAllPermissions = false;
                } else {
                    haveAllPermissions = true;
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
