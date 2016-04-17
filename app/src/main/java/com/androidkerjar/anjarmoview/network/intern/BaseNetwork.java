package com.androidkerjar.anjarmoview.network.intern;

import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class BaseNetwork {
    public String setNetwork(String urldata, String apikey) {
        String response = null;
        try {
            Uri builduri = Uri.parse(urldata).buildUpon()
                    .appendQueryParameter("api_key", apikey)
                    .build();
            URL url = new URL(builduri.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextline = null;
            while ((nextline = reader.readLine()) != null) {
                sb.append(nextline);
            }
            response = sb.toString();
        } catch (Exception e) {

        }
        return response;
    }
}
