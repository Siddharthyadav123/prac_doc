package com.sidproj.nagpurdrs.screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.DoctorLoginProfileDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APICallback;

import java.util.ArrayList;

public class DrHomeScreenActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, APICallback {


    private DoctorLoginProfileDo doctorLoginProfileDo;

    private TextView userFullName;
    private TextView userMobileNum;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_home_screen);
        doctorLoginProfileDo = LocalModel.getInstance().getDoctorLoginProfileDo();
        setupActionBar(false, "Nagpur Doctors");
        setupNavigationDrawer();
        initViews();
        registerEvents();
        setInfoInUI();
        MyApplication.getInstance().enableGPS(this);
        MyApplication.getInstance().requestDrNotification();
    }

    @Override
    protected void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements) {

    }


    private void initViews() {
        userFullName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usrFullName);
        userMobileNum = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usrMobileNum);
    }

    private void setInfoInUI() {
        userFullName.setText(doctorLoginProfileDo.getDrFullName());
        userMobileNum.setText(doctorLoginProfileDo.getDrContactNum());
    }

    private void registerEvents() {
    }

    private void setupNavigationDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        drawer.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.yourAppointments:
                Intent i = new Intent(DrHomeScreenActivity.this, AppointmentListActivity.class);
                startActivity(i);
                return false;
            case R.id.aboutUs:
                return false;
            case R.id.logout:
                showLogoutDailog();
                return false;
        }
        return false;
    }

    public void showLogoutDailog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Message");
        builder1.setMessage("Are you sure you want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LocalModel.getInstance().removeDoctorProfileAndAppointmentsInfoOnLogout(DrHomeScreenActivity.this);
                        dialog.cancel();
                        Intent i = new Intent(DrHomeScreenActivity.this, SplashActivity.class);
                        startActivity(i);
                    }
                });

        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_DR_SPECIALIZATION_LIST:
                break;
        }
    }
}
