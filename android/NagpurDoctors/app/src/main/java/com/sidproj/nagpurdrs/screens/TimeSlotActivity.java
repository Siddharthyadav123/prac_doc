package com.sidproj.nagpurdrs.screens;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.DrProfileDo;
import com.sidproj.nagpurdrs.entities.DrTimeSlotsDo;
import com.sidproj.nagpurdrs.volly.APIHandler;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeSlotActivity extends BaseActivity {
    //dr profile object
    private DrProfileDo drProfileDo;
    private TextView timeSlotTextView;
    private TextView takeBtnTextView;

    private RelativeLayout rlMorningSlots;
    private RelativeLayout rlAfterNoonSlots;
    private RelativeLayout rlEveningSlots;
    private RelativeLayout rlNightSlots;

    private LinearLayout llMorningSlots;
    private LinearLayout llAfterNoonSlots;
    private LinearLayout llEveningSlots;
    private LinearLayout llNightSlots;


    private int selectedYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
    private int selectedMonth = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MONTH) + 1;
    private int selectedDay = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);
        drProfileDo = getIntent().getParcelableExtra("key_dr_profile");
        setupActionBar(true, "Available TimeSlots");
        initViews();
        registerEvents();
        requestTimeSlots();
    }

    private void requestTimeSlots() {
        String url = URLConstants.URL_GET_DR_TIMESLOTS + drProfileDo.getId() + "/time_slots";
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_DR_TIME_SLOT,
                Request.Method.POST, url, true, "Loading Time Slots...", drProfileDo.formJsonRequestBodyForTimeSlot());
        apiHandler.requestAPI();
    }


    private void initViews() {
        timeSlotTextView = (TextView) findViewById(R.id.timeSlotTextView);
        takeBtnTextView = (TextView) findViewById(R.id.takeBtnTextView);
        timeSlotTextView.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear + " (Today)");

        rlMorningSlots = (RelativeLayout) findViewById(R.id.rlMorningSlots);
        rlAfterNoonSlots = (RelativeLayout) findViewById(R.id.rlAfterNoonSlots);
        rlEveningSlots = (RelativeLayout) findViewById(R.id.rlEveningSlots);
        rlNightSlots = (RelativeLayout) findViewById(R.id.rlNightSlots);

        llMorningSlots = (LinearLayout) findViewById(R.id.llMorningSlots);
        llAfterNoonSlots = (LinearLayout) findViewById(R.id.llAfterNoonSlots);
        llEveningSlots = (LinearLayout) findViewById(R.id.llEveningSlots);
        llNightSlots = (LinearLayout) findViewById(R.id.llNightSlots);
    }

    private void registerEvents() {
        timeSlotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
        takeBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_DR_TIME_SLOT:
                onTimeSlotResponse(requestId, isSuccess, response, errorString);
                break;
        }
    }

    private void onTimeSlotResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            Gson gson = new Gson();
            DrTimeSlotsDo drTimeSlotsDo = gson.fromJson(response, DrTimeSlotsDo.class);
        }
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            selectedYear = arg1;
            selectedMonth = arg2 + 1;
            selectedDay = arg3;
            timeSlotTextView.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear);

        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, selectedYear, selectedMonth, selectedDay);
        }
        return null;
    }
}
