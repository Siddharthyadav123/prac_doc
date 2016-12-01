package com.sidproj.nagpurdrs.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.adapters.DrListAdapter;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.DrProfileDo;
import com.sidproj.nagpurdrs.entities.DrSpeciliazation;
import com.sidproj.nagpurdrs.volly.APICallback;
import com.sidproj.nagpurdrs.volly.APIHandler;

import java.util.ArrayList;

public class DrListActivity extends BaseActivity implements APICallback {

    private ListView drListView;
    private DrListAdapter drListAdapter;
    private DrSpeciliazation selectedSpecialization;

    private ArrayList<DrProfileDo> drList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_list);

        selectedSpecialization = getIntent().getParcelableExtra("selected_specialization");
        setupActionBar(true, selectedSpecialization.getName());

        initViews();
        registerEvents();
        setDrListAdapter();
        requestDrProfiles();
    }

    private void requestDrProfiles() {
        String url = URLConstants.URL_GET_DR_PROFILE_BY_SPECIALIZATION + selectedSpecialization.getId();
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_DR_PROFILE_BY_SPEC_ID,
                Request.Method.GET, url, true, "Loading " + selectedSpecialization.getName() + " List...", null, true);
        apiHandler.requestAPI();
    }

    private void setDrListAdapter() {
        drListAdapter = new DrListAdapter(this, drList);
        drListView.setAdapter(drListAdapter);
    }


    private void initViews() {
        drListView = (ListView) findViewById(R.id.drListView);
    }

    private void registerEvents() {
        drListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DrListActivity.this, DrProfileActivity.class);
                i.putExtra("key_dr_profile", drList.get(position));
                startActivity(i);
            }
        });

    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_DR_PROFILE_BY_SPEC_ID:
                Gson gson = new Gson();
                drList = (ArrayList<DrProfileDo>) gson.fromJson(response,
                        new TypeToken<ArrayList<DrProfileDo>>() {
                        }.getType());
                drListAdapter.setSpecializationText(selectedSpecialization.getName());
                drListAdapter.refreshAdapter(drList);

                break;
        }
    }
}
