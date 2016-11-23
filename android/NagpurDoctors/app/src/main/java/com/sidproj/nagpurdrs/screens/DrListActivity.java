package com.sidproj.nagpurdrs.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sidproj.nagpurdrs.R;

public class DrListActivity extends BaseActivity {

    private ListView drListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_list);
        setupActionBar(true, "Specialization");
        initViews();
        registerEvents();
    }


    private void initViews() {
        drListView = (ListView) findViewById(R.id.drListView);
    }

    private void registerEvents() {
        drListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
