package com.androidkerjar.anjarmoview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidkerjar.anjarmoview.R;
import com.androidkerjar.anjarmoview.item.Movie_Item;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class Movie_Adapter2 extends ArrayAdapter<Movie_Item> {
    ArrayList<Movie_Item> itemlist;
    Context context;
    int resource;
    LayoutInflater vi;
    SimpleDateFormat sdf;
    public Movie_Adapter2(Context context, int resource, ArrayList<Movie_Item> itemlist) {
        super(context, resource, itemlist);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemlist = itemlist;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = vi.inflate(resource, null);
        if (vi != null) {
            final String NEW_FORMAT = "dd MMMM, yyyy";
            String newDateString = itemlist.get(position).getDate();
            final String OLD_FORMAT = "yyyy-MM-dd";
            sdf = new SimpleDateFormat(OLD_FORMAT);

            try {
                Date d = sdf.parse(itemlist.get(position).getDate());
                sdf.applyPattern(NEW_FORMAT);
                newDateString = sdf.format(d);
            } catch (Exception e) {

            }

            TextView txtTitle = (TextView) v.findViewById(R.id.txttitle);
            TextView txtdate = (TextView) v.findViewById(R.id.txtdate);
            TextView txtdescription = (TextView) v.findViewById(R.id.txtDesc);
            RatingBar ratting = (RatingBar) v.findViewById(R.id.ratting);
            ImageView imageTitle = (ImageView) v.findViewById(R.id.imageTitle);

            txtTitle.setText(itemlist.get(position).getTitle());
            txtdate.setText(newDateString);
            txtdescription.setText(itemlist.get(position).getDescription());
            ratting.setRating(itemlist.get(position).getRatting());
            Glide.with(context)
                    .load(itemlist.get(position).getImage())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageTitle);

        }


        return v;
    }
}
