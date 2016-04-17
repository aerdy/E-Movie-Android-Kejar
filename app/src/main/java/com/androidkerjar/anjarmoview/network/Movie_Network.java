package com.androidkerjar.anjarmoview.network;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.androidkerjar.anjarmoview.MainActivity;
import com.androidkerjar.anjarmoview.R;
import com.androidkerjar.anjarmoview.adapter.Movie_Adapter;
import com.androidkerjar.anjarmoview.adapter.Movie_Adapter2;
import com.androidkerjar.anjarmoview.item.Movie_Item;
import com.androidkerjar.anjarmoview.network.intern.BaseNetwork;
import com.androidkerjar.anjarmoview.network.intern.BaseUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class Movie_Network extends AsyncTask<Integer, Void, String> implements BaseUrl {
    ArrayList<Movie_Item> itemlist;
    Movie_Adapter2 adapter;
    ProgressBar progressBar;

    public Movie_Network(ProgressBar progressBar, ArrayList<Movie_Item> itemlist, Movie_Adapter2 adapter) {
        this.itemlist = itemlist;
        this.adapter = adapter;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            String response = null;
            switch (params[0]) {
                case 0:
                    response = new BaseNetwork().setNetwork(now_playing, apikey);
                    break;
                case 1:
                    response = new BaseNetwork().setNetwork(popular, apikey);
                    break;
                case 2:
                    response = new BaseNetwork().setNetwork(topratting, apikey);
                    break;
            }
            parsingData(response);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    void parsingData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int a = 0; a < jsonArray.length(); a++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(a);

                String id = jsonObject1.getString("id");
                String title = jsonObject1.getString("original_title");
                String date = jsonObject1.getString("release_date");
                String description = jsonObject1.getString("overview");
                String image = "http://image.tmdb.org/t/p/w500" + jsonObject1.getString("poster_path");
                String ratting = jsonObject1.getString("vote_average");

                Movie_Item item = new Movie_Item();
                item.setId(id);
                item.setImage(image);
                item.setDate(date);
                item.setTitle(title);
                item.setDescription(description);
                item.setRatting(Float.parseFloat(ratting));
                itemlist.add(item);
            }

        } catch (Exception e) {

        }
    }
}
