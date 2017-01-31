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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.adapters.SpecalizationListAdapter;
import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.DrSpeciliazation;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APICallback;
import com.sidproj.nagpurdrs.volly.APIHandler;

import java.util.ArrayList;


public class HomeScreenActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, APICallback {

    private ListView doctorCateogryListView;
    private SpecalizationListAdapter specalizationListAdapter;
    private ArrayList<DrSpeciliazation> drSpeciliazationArrayList = new ArrayList<>();

    private UserProfileDo userProfileDo;

    private TextView userFullName;
    private TextView userMobileNum;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        userProfileDo = LocalModel.getInstance().getUserProfileDo();
        setupActionBar(false, "Nagpur Doctors");
        setupNavigationDrawer();
        initViews();
        registerEvents();
        showSpecializationListAdapter();
        requestDrSpecialization();
        setInfoInUI();
        MyApplication.getInstance().enableGPS(this);
    }


    private void requestDrSpecialization() {
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_DR_SPECIALIZATION_LIST,
                Request.Method.GET, URLConstants.URL_GET_SPECIALIZATIONlIST, true, "Loading Specialization...", null);
        apiHandler.requestAPI();
    }


    private void showSpecializationListAdapter() {
        specalizationListAdapter = new SpecalizationListAdapter(this, drSpeciliazationArrayList);
        doctorCateogryListView.setAdapter(specalizationListAdapter);
    }

    private void initViews() {
        doctorCateogryListView = (ListView) findViewById(R.id.doctorCateogryListView);

        userFullName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usrFullName);
        userMobileNum = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usrMobileNum);
    }

    private void setInfoInUI() {
        userFullName.setText(userProfileDo.getFull_name());
        userMobileNum.setText(userProfileDo.getMobile_no());
    }

    private void registerEvents() {
        doctorCateogryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(HomeScreenActivity.this, DrListActivity.class);
                i.putExtra("selected_specialization", drSpeciliazationArrayList.get(position));
                startActivity(i);
            }
        });
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
                return false;
            case R.id.aboutUs:
                return false;
            case R.id.feedback:
                return false;
            case R.id.help:
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
                        LocalModel.getInstance().removeUserProfileOnLogout(HomeScreenActivity.this);
                        dialog.cancel();
                        Intent i = new Intent(HomeScreenActivity.this, SplashActivity.class);
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
                Gson gson = new Gson();
                drSpeciliazationArrayList = (ArrayList<DrSpeciliazation>) gson.fromJson(response,
                        new TypeToken<ArrayList<DrSpeciliazation>>() {
                        }.getType());
                specalizationListAdapter.refreshAdapter(drSpeciliazationArrayList);
                break;
        }
    }
}
