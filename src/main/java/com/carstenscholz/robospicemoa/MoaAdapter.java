package com.carstenscholz.robospicemoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Carsten on 9/10/2015.
 */
public class MoaAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    MoaObject moaObject;

    public MoaAdapter(Context _context, LayoutInflater _inflater){
        context = _context;
        inflater = _inflater;
        moaObject = new MoaObject();
    }
    @Override
    public int getCount() {
        //Only one object is returned by this API
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return moaObject;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = inflater.inflate(R.layout.list_row, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.param_name);
            holder.valueTextView = (TextView) convertView.findViewById(R.id.param_value);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(moaObject.getParamName());
        holder.valueTextView.setText(moaObject.getParamValue());

        return convertView;
    }
    public void updateData(MoaObject _moaObject) {
        // update the adapter's dataset
        moaObject = _moaObject;
        //Refreshes the list
        notifyDataSetChanged();
    }
    // this is used so you only ever have to do
    // inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView nameTextView;
        public TextView valueTextView;
    }
}
