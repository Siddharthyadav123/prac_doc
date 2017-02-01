package com.sidproj.nagpurdrs.screens;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.DrProfileDo;
import com.sidproj.nagpurdrs.entities.DrTimeSlotsDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APIHandler;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeSlotActivity extends BaseActivity {
    //dr profile object
    private DrProfileDo drProfileDo;
    private UserProfileDo userProfileDo;

    private TextView dateSlotTextView;
    private TextView takeBtnTextView;

    private RelativeLayout rlMorningSlots;
    private RelativeLayout rlAfterNoonSlots;
    private RelativeLayout rlEveningSlots;
    private RelativeLayout rlNightSlots;

    private LinearLayout llMorningSlots;
    private LinearLayout llAfterNoonSlots;
    private LinearLayout llEveningSlots;
    private LinearLayout llNightSlots;

    private TextView appointmentTimeLabel;
    private TextView timeSlotTextView;

    private int selectedYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
    private int selectedMonth = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MONTH) + 1;
    private int selectedDay = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);
        drProfileDo = getIntent().getParcelableExtra("key_dr_profile");
        userProfileDo = LocalModel.getInstance().getUserProfileDo();

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

    private void requestTakeAppointment() {
        String url = URLConstants.URL_POST_TAKE_APPOINTMENT;
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_DR_TAKE_APPOINTMENT,
                Request.Method.POST, url, true, "Scheduling your Appointment...", formRequestBodyForAppointment());
        apiHandler.requestAPI();
    }

    /**
     * {"patient_id":1,"patient_name":"siddharth","dr_id":1,
     * "dr_name":"Dr. Nikhalesh Nilawar","date_time":"2016-10-21 16:00:59",
     * "status":1}
     *
     * @return
     */
    private String formRequestBodyForAppointment() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("patient_id", userProfileDo.getId());
            jsonObject.put("patient_name", userProfileDo.getFull_name());

            jsonObject.put("dr_id", drProfileDo.getId());
            jsonObject.put("dr_name", drProfileDo.getDrFullName());

            String dateTime = selectedYear + "-" + selectedMonth + "-" + selectedDay + " " + convertTimeTo24Hour(timeSlotTextView.getText().toString());
            jsonObject.put("date_time", dateTime);
            jsonObject.put("status", 1);
            return jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertTimeTo24Hour(String amPMTime) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = parseFormat.parse(amPMTime);
            return displayFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    private void initViews() {
        dateSlotTextView = (TextView) findViewById(R.id.dateSlotTextView);
        takeBtnTextView = (TextView) findViewById(R.id.takeBtnTextView);
        dateSlotTextView.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear + " (Today)");

        rlMorningSlots = (RelativeLayout) findViewById(R.id.rlMorningSlots);
        rlAfterNoonSlots = (RelativeLayout) findViewById(R.id.rlAfterNoonSlots);
        rlEveningSlots = (RelativeLayout) findViewById(R.id.rlEveningSlots);
        rlNightSlots = (RelativeLayout) findViewById(R.id.rlNightSlots);

        llMorningSlots = (LinearLayout) findViewById(R.id.llMorningSlots);
        llAfterNoonSlots = (LinearLayout) findViewById(R.id.llAfterNoonSlots);
        llEveningSlots = (LinearLayout) findViewById(R.id.llEveningSlots);
        llNightSlots = (LinearLayout) findViewById(R.id.llNightSlots);

        appointmentTimeLabel = (TextView) findViewById(R.id.appointmentTimeLabel);
        timeSlotTextView = (TextView) findViewById(R.id.timeSlotTextView);
    }

    private void showSelectedTime(String timeText) {
        appointmentTimeLabel.setVisibility(View.VISIBLE);
        timeSlotTextView.setText(timeText);
        timeSlotTextView.setVisibility(View.VISIBLE);
        takeBtnTextView.setVisibility(View.VISIBLE);
    }

    private void registerEvents() {
        dateSlotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
        takeBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTakeAppointment();
            }
        });
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_DR_TIME_SLOT:
                onTimeSlotResponse(requestId, isSuccess, response, errorString);
                break;
            case RequestConstant.REQUEST_DR_TAKE_APPOINTMENT:
                onAppointmenTakenResponse(requestId, isSuccess, response, errorString);
                break;
        }
    }

    private void onAppointmenTakenResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            Toast.makeText(TimeSlotActivity.this, "Your Request for Appointment has been sent. " +
                    "you will also get the notification as soon as doctor confirms.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private DrTimeSlotsDo drTimeSlotsDo = null;

    private void onTimeSlotResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            Gson gson = new Gson();
            drTimeSlotsDo = gson.fromJson(response, DrTimeSlotsDo.class);
            feedAndShowSlots();
        }
    }

    private void feedAndShowSlots() {
        String[] morningTimeSlots = drTimeSlotsDo.getMorningTimeSlots();
        String[] afternoonTimeSlots = drTimeSlotsDo.getAfternoonTimeSlots();
        String[] eveningTimeSlots = drTimeSlotsDo.getEveningTimeSlots();
        String[] nightTimeSlots = drTimeSlotsDo.getNightTimeSlots();
        String[] busySlopts = drTimeSlotsDo.getBusySlopts();

        showItems(morningTimeSlots, rlMorningSlots, llMorningSlots);
        showItems(afternoonTimeSlots, rlAfterNoonSlots, llAfterNoonSlots);
        showItems(eveningTimeSlots, rlEveningSlots, llEveningSlots);
        showItems(nightTimeSlots, rlNightSlots, llNightSlots);
    }

    private void showItems(final String[] slots, RelativeLayout parentLayout, LinearLayout containerLinearLayout) {
        containerLinearLayout.removeAllViews();
        if (slots != null && slots.length > 0) {
            parentLayout.setVisibility(View.VISIBLE);
            int numOfSlots = slots.length;

            LinearLayout rowOne = null;
            LinearLayout rowTwo = null;
            LinearLayout rowThree = null;

            if (numOfSlots > 1 && numOfSlots < 4) {
                rowOne = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowOne.setLayoutParams(layoutParams);

            } else if (numOfSlots > 3 && numOfSlots < 7) {
                rowOne = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowOne.setLayoutParams(layoutParams);

                rowTwo = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowTwo.setLayoutParams(layoutParams2);
            } else if (numOfSlots > 6) {
                rowOne = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowOne.setLayoutParams(layoutParams);

                rowTwo = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowTwo.setLayoutParams(layoutParams2);

                rowThree = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                rowThree.setLayoutParams(layoutParams3);
            }


            for (int i = 0; i < numOfSlots; i++) {
                LinearLayout slotView = (LinearLayout) getLayoutInflater().inflate(R.layout.time_slot_item, null);
                LinearLayout.LayoutParams slotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                slotView.setLayoutParams(slotParams);

                final TextView textView = (TextView) slotView.findViewById(R.id.timeTextView);
                textView.setText(slots[i]);

                if (timeSlotTextView.getText().toString().equalsIgnoreCase(slots[i])) {
                    textView.setBackgroundColor(getResources().getColor(R.color.TheamLightColor));
                }

                final int finalI = i;
                slotView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //refreshing drawing
                        showSelectedTime(slots[finalI]);
                        feedAndShowSlots();
                    }
                });

                if (i > 5) {
                    rowThree.addView(slotView);
                } else if (i > 2) {
                    rowTwo.addView(slotView);
                } else {
                    rowOne.addView(slotView);
                }
            }

            containerLinearLayout.addView(rowOne);

            if (rowTwo != null)
                containerLinearLayout.addView(rowTwo);

            if (rowThree != null)
                containerLinearLayout.addView(rowThree);

        } else {
            parentLayout.setVisibility(View.GONE);
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
            dateSlotTextView.setText(selectedDay + "-" + selectedMonth + "-" + selectedYear);

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
