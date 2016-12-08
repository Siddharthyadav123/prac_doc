package com.sidproj.nagpurdrs.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
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
        userProfileDo = getIntent().getParcelableExtra("user_details");
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
                Request.Method.GET, URLConstants.URL_GET_SPECIALIZATIONlIST, true, "Loading Specialization...", null, true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.yourAppointments) {

        } else if (id == R.id.profile) {

        } else if (id == R.id.aboutUs) {

        } else if (id == R.id.feedback) {

        } else if (id == R.id.help) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
