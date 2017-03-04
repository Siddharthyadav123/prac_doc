package com.sidproj.nagpurdrs.screens;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.adapters.AppointmentListAdapter;
import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.volly.APIHandler;

import java.util.ArrayList;

public class AppointmentListActivity extends BaseActivity implements View.OnClickListener {
    public static final int TAP_PENDING = 1;
    public static final int TAP_APPROVED = 2;
    public static final int TAP_COMPLETED = 3;
    public static final int TAP_CANCELLED = 4;

    private int mSelectedTap = TAP_PENDING;
    private TextView mTVPending;
    private TextView mTVApporved;
    private TextView mTVCompleted;
    private TextView mTVCancelled;
    private ListView mLVAppointment;
    private TextView mTVMsg;
    private AppointmentListAdapter appointmentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSelectedTap = getIntent().getIntExtra("tab_selection", TAP_PENDING);
        setContentView(R.layout.activity_appointment_list);
        setupActionBar(true, "Your Appointments");
        initViews();
        hideNotificationBtn();
        registerEvents();
        loadAppointmentList();
    }

    @Override
    protected void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements) {
        loadAppointmentList();
    }

    private void initViews() {
        mTVPending = (TextView) findViewById(R.id.tv_pending);
        mTVApporved = (TextView) findViewById(R.id.tv_approved);
        mTVCompleted = (TextView) findViewById(R.id.tv_completed);
        mTVCancelled = (TextView) findViewById(R.id.tv_cancelled);
        mTVMsg = (TextView) findViewById(R.id.tv_msg);
        mLVAppointment = (ListView) findViewById(R.id.lv_appointments);
    }

    private void registerEvents() {
        mTVPending.setOnClickListener(this);
        mTVApporved.setOnClickListener(this);
        mTVCompleted.setOnClickListener(this);
        mTVCancelled.setOnClickListener(this);
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_PUT_DR_APPOINTMENT_UPDATE:
                Toast.makeText(this, errorString, Toast.LENGTH_LONG).show();

                boolean isDocLogin = LocalModel.getInstance().isDoctorLogin();
                if (isDocLogin) {
                    MyApplication.getInstance().requestDrNotification(true);
                } else {
                    MyApplication.getInstance().requestPatientNotification(true);
                }

                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pending:
                mSelectedTap = TAP_PENDING;
                break;
            case R.id.tv_approved:
                mSelectedTap = TAP_APPROVED;
                break;
            case R.id.tv_completed:
                mSelectedTap = TAP_COMPLETED;
                break;
            case R.id.tv_cancelled:
                mSelectedTap = TAP_CANCELLED;
                break;
        }
        loadAppointmentList();
    }

    private void loadAppointmentList() {
        highLightTabSelection();

        ArrayList<AppointmentDo> appointmentList = LocalModel.getInstance().getAppointmentList();
        if (appointmentList != null && appointmentList.size() > 0) {
            ArrayList<AppointmentDo> tabWiseAppointmentList = filterTabWiseAppointment(appointmentList);
            if (tabWiseAppointmentList.size() > 0) {
                mTVMsg.setVisibility(View.GONE);
                refreshAdapter(tabWiseAppointmentList);
            } else {
                setEmptyMsg();
                refreshAdapter(null);
            }
        } else {
            setEmptyMsg();
            refreshAdapter(null);
        }
    }

    public void requestAppointmentUpdate(AppointmentDo appointmentDo, int newStatus) {
        String url = URLConstants.URL_PUT_DR_APPOINTMENT_UPDATE;
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_PUT_DR_APPOINTMENT_UPDATE,
                Request.Method.PUT, url, true, "Updating Appointment...", appointmentDo.formAppointmentRequestBody(newStatus));
        apiHandler.requestAPI();
    }

    private void refreshAdapter(ArrayList<AppointmentDo> appointmentList) {
        if (appointmentListAdapter == null) {
            appointmentListAdapter = new AppointmentListAdapter(this, appointmentList);
            mLVAppointment.setAdapter(appointmentListAdapter);
        } else {
            appointmentListAdapter.refreshAdapter(appointmentList);
        }
    }

    private ArrayList<AppointmentDo> filterTabWiseAppointment(ArrayList<AppointmentDo> appointmentList) {
        ArrayList<AppointmentDo> fileredAppointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getStatus() == mSelectedTap) {
                fileredAppointmentList.add(appointmentList.get(i));
            }
        }
        return fileredAppointmentList;
    }


    private void highLightTabSelection() {
        switch (mSelectedTap) {
            case TAP_PENDING:
                mTVPending.setBackgroundColor(getResources().getColor(R.color.TheamLightColor));
                mTVApporved.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCompleted.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCancelled.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                break;
            case TAP_APPROVED:
                mTVPending.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVApporved.setBackgroundColor(getResources().getColor(R.color.TheamLightColor));
                mTVCompleted.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCancelled.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                break;
            case TAP_COMPLETED:
                mTVPending.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVApporved.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCompleted.setBackgroundColor(getResources().getColor(R.color.TheamLightColor));
                mTVCancelled.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                break;
            case TAP_CANCELLED:
                mTVPending.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVApporved.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCompleted.setBackgroundColor(getResources().getColor(R.color.TheamDarkColor));
                mTVCancelled.setBackgroundColor(getResources().getColor(R.color.TheamLightColor));
                break;
        }
    }

    private void setEmptyMsg() {
        mTVMsg.setVisibility(View.VISIBLE);
        switch (mSelectedTap) {
            case TAP_PENDING:
                mTVMsg.setText("You have no pending Appointments.");
                break;
            case TAP_APPROVED:
                mTVMsg.setText("No Appointment has approved yet.");
                break;
            case TAP_COMPLETED:
                mTVMsg.setText("You have not completed any Appointment.");
                break;
            case TAP_CANCELLED:
                mTVMsg.setText("You have cancelled no Appointment.");
                break;
        }
    }


}
