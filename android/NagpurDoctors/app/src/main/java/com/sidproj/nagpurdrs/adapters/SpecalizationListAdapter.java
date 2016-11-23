package com.sidproj.nagpurdrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sidproj.nagpurdrs.R;

/**
 * Created by siddharth on 11/23/2016.
 */
public class SpecalizationListAdapter extends BaseAdapter {

    private Context context;

    public SpecalizationListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 10;
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
        return convertView;
    }
}
