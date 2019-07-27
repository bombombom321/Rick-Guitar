package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.core.model.Guitar;
import com.example.myapplication.core.model.Inventory;

import java.util.List;

public class GuitarListAdapter extends BaseAdapter {

    private Context context;
    private List<Guitar> guitars;

    public GuitarListAdapter(Context context, Inventory inventory) {
        this.context = context;
        this.guitars = inventory.getGuitars();
    }

    @Override
    public int getCount() {
        return guitars.size();
    }

    @Override
    public Object getItem(int position) {
        return guitars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.model.setText(guitars.get(position).getSpec().getModel());
        holder.sn.setText(guitars.get(position).getSerialNumber());
        return convertView;
    }

    private class ViewHolder {
        TextView model;
        TextView sn;

        ViewHolder(View view) {
            model = view.findViewById(R.id.list_item_model);
            sn = view.findViewById(R.id.list_item_sn);
            view.setTag(this);
        }
    }
}
