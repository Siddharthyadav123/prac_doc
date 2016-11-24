package com.sidproj.nagpurdrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.entities.DrProfileDo;

import java.util.ArrayList;

/**
 * Created by siddharth on 11/23/2016.
 */
public class DrListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrProfileDo> drList;

    public DrListAdapter(Context context, ArrayList<DrProfileDo> drList) {
        this.context = context;
        this.drList = drList;
    }


    @Override
    public int getCount() {
        return drList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.dr_list_item_layout, null);
        }

        TextView drFullNameTextView = (TextView) convertView.findViewById(R.id.drFullNameTextView);
        TextView drClinicAddressTextView = (TextView) convertView.findViewById(R.id.drClinicAddressTextView);
        TextView drFeesTextView = (TextView) convertView.findViewById(R.id.drFeesTextView);
        TextView drExperienceTextView = (TextView) convertView.findViewById(R.id.drExperienceTextView);

        DrProfileDo drProfileDo = drList.get(position);
        drFullNameTextView.setText(drProfileDo.getDrFullName());
        drClinicAddressTextView.setText(drProfileDo.getDrClinicAddress());
        drFeesTextView.setText(drProfileDo.getDrConsulationFee() + " /-");
        drExperienceTextView.setText(drProfileDo.getDrExperience() + " yrs exp");

        return convertView;
    }

    public void refreshAdapter(ArrayList<DrProfileDo> drList) {
        this.drList = drList;
        notifyDataSetChanged();
    }
}