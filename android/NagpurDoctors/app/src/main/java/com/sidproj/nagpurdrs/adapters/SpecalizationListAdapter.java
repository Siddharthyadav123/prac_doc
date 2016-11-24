package com.sidproj.nagpurdrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.entities.DrSpeciliazation;

import java.util.ArrayList;

/**
 * Created by siddharth on 11/23/2016.
 */
public class SpecalizationListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DrSpeciliazation> drSpeciliazationArrayList;

    public SpecalizationListAdapter(Context context, ArrayList<DrSpeciliazation> drSpeciliazationArrayList) {
        this.context = context;
        this.drSpeciliazationArrayList = drSpeciliazationArrayList;
    }


    @Override
    public int getCount() {
        return drSpeciliazationArrayList.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.specialization_list_item, null);
        }

        TextView listTextView = (TextView) convertView.findViewById(R.id.listTextView);
        listTextView.setText(drSpeciliazationArrayList.get(position).getName());
        return convertView;
    }

    public void refreshAdapter(ArrayList<DrSpeciliazation> drSpeciliazationArrayList) {
        this.drSpeciliazationArrayList = drSpeciliazationArrayList;
        notifyDataSetChanged();
    }
}
