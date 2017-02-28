package com.sidproj.nagpurdrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.model.LocalModel;

import java.util.ArrayList;

/**
 * Created by siddharth on 2/3/2017.
 */
public class AppointmentListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AppointmentDo> appointmentDos = null;
    private boolean isDocLogin = false;

    public AppointmentListAdapter(Context context, ArrayList<AppointmentDo> appointmentDos) {
        this.context = context;
        this.appointmentDos = appointmentDos;
        isDocLogin = LocalModel.getInstance().isDoctorLogin();
    }

    public void refreshAdapter(ArrayList<AppointmentDo> appointmentDos) {
        this.appointmentDos = appointmentDos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (appointmentDos != null) {
            return appointmentDos.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appointment_list_item, null);
        }
        TextView labelDrName = (TextView) convertView.findViewById(R.id.labelDrName);
        TextView labelAppointmentDate = (TextView) convertView.findViewById(R.id.labelAppointmentDate);
        TextView labelStatus = (TextView) convertView.findViewById(R.id.labelStatus);

        Button btnOne = (Button) convertView.findViewById(R.id.btnOne);
        Button BtnTwo = (Button) convertView.findViewById(R.id.BtnTwo);

        TextView drNameTextView = (TextView) convertView.findViewById(R.id.drNameTextView);
        TextView appointmentDateTextView = (TextView) convertView.findViewById(R.id.appointmentDateTextView);
        TextView appointmentStatusTextView = (TextView) convertView.findViewById(R.id.appointmentStatusTextView);

        AppointmentDo appointmentDo = appointmentDos.get(position);

        if (isDocLogin) {
            labelDrName.setText("Patient Name:");
            drNameTextView.setText(appointmentDo.getPatient_name());
        } else {
            drNameTextView.setText(appointmentDo.getDr_name());
        }

        appointmentDateTextView.setText(appointmentDo.getDate_time());

        switch (appointmentDo.getStatus()) {
            case AppointmentDo.STATUS_PENDING:
                appointmentStatusTextView.setText("PENDING");
                break;
            case AppointmentDo.STATUS_APPROVED:
                appointmentStatusTextView.setText("APPROVED");
                break;
            case AppointmentDo.STATUS_CANCELLED:
                appointmentStatusTextView.setText("CANCELLED");
                break;
            case AppointmentDo.STATUS_PROCESSED:
                appointmentStatusTextView.setText("PROCESSED");
                break;
        }

        setButtonAsPerUserTypeAndStatus(appointmentDo, btnOne, BtnTwo);

        return convertView;
    }

    private void setButtonAsPerUserTypeAndStatus(final AppointmentDo appointmentDo, Button btnOne, Button btnTwo) {
        btnOne.setVisibility(View.VISIBLE);
        btnTwo.setVisibility(View.VISIBLE);

        if (isDocLogin) {
            switch (appointmentDo.getStatus()) {
                case AppointmentDo.STATUS_PENDING:
                    btnOne.setText("Approve It");
                    btnTwo.setText("Cancel It");
                    btnOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onDrApproveClick(appointmentDo);
                        }
                    });
                    btnTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onDrCancelClick(appointmentDo);
                        }
                    });
                    break;
                case AppointmentDo.STATUS_APPROVED:
                    btnOne.setText("Save as Completed");
                    btnTwo.setText("Cancel It");
                    btnOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onDrCompletedClick(appointmentDo);
                        }
                    });
                    btnTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onDrCancelClick(appointmentDo);
                        }
                    });
                    break;
                case AppointmentDo.STATUS_CANCELLED:
                    btnOne.setText("Approve It");
                    btnTwo.setVisibility(View.GONE);
                    btnOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onDrApproveClick(appointmentDo);
                        }
                    });
                    break;
                case AppointmentDo.STATUS_PROCESSED:
                    btnOne.setVisibility(View.GONE);
                    btnTwo.setVisibility(View.GONE);
                    break;
            }
        } else {
            switch (appointmentDo.getStatus()) {
                case AppointmentDo.STATUS_PENDING:
                    btnOne.setVisibility(View.GONE);
                    btnTwo.setVisibility(View.GONE);
                    break;
                case AppointmentDo.STATUS_APPROVED:
                    btnOne.setText("Cancel It");
                    btnTwo.setVisibility(View.GONE);
                    btnOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onPatientCancelClick(appointmentDo);
                        }
                    });
                    break;
                case AppointmentDo.STATUS_CANCELLED:
                    btnOne.setVisibility(View.GONE);
                    btnTwo.setVisibility(View.GONE);
                    break;
                case AppointmentDo.STATUS_PROCESSED:
                    btnOne.setVisibility(View.GONE);
                    btnTwo.setVisibility(View.GONE);
                    break;
            }
        }

    }

    private void onPatientCancelClick(AppointmentDo appointmentDo) {

    }

    private void onDrCompletedClick(AppointmentDo appointmentDo) {

    }

    private void onDrCancelClick(AppointmentDo appointmentDo) {

    }

    private void onDrApproveClick(AppointmentDo appointmentDo) {

    }
}
