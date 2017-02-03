package com.sidproj.nagpurdrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.entities.AppointmentDo;

import java.util.ArrayList;

/**
 * Created by siddharth on 2/3/2017.
 */
public class AppointmentListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AppointmentDo> appointmentDos = null;

    public AppointmentListAdapter(Context context, ArrayList<AppointmentDo> appointmentDos) {
        this.context = context;
        this.appointmentDos = appointmentDos;
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

        TextView drNameTextView = (TextView) convertView.findViewById(R.id.drNameTextView);
        TextView appointmentDateTextView = (TextView) convertView.findViewById(R.id.appointmentDateTextView);
        TextView appointmentStatusTextView = (TextView) convertView.findViewById(R.id.appointmentStatusTextView);

        AppointmentDo appointmentDo = appointmentDos.get(position);

        drNameTextView.setText(appointmentDo.getDr_name());
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

        return convertView;
    }
}
