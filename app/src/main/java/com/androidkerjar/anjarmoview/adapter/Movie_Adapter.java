package com.androidkerjar.anjarmoview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidkerjar.anjarmoview.R;

import java.util.ArrayList;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class Movie_Adapter extends ArrayAdapter<String> {
    ArrayList<String> itemlist;
    Context context;
    int resource;
    LayoutInflater vi;

    public Movie_Adapter(Context context, int resource, ArrayList<String> itemlist) {
        super(context, resource, itemlist);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.itemlist = itemlist;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(resource, null);
            holder.txtTitle = (TextView) v.findViewById(R.id.txttitle);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.txtTitle.setText(itemlist.get(position));
        return v;
    }

    static class ViewHolder {
        public TextView txtTitle;

    }

}

