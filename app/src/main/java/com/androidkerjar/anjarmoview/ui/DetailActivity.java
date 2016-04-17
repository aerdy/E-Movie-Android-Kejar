package com.androidkerjar.anjarmoview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidkerjar.anjarmoview.R;
import com.androidkerjar.anjarmoview.network.Detail_Network;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTitle, txtDate, txtDesc;
    ProgressBar progressBar;
    ImageView imageTitle, imagePoster;
    String urltriler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setTitle("Detail Movie");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtTitle = (TextView) findViewById(R.id.txttitle);
        txtDate = (TextView) findViewById(R.id.txtdate);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        imageTitle = (ImageView) findViewById(R.id.imageTitle);
        imagePoster = (ImageView) findViewById(R.id.imgposter);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        imageTitle.setOnClickListener(this);
        new Detail_Network(this, id).execute();
    }

    public void setData(String title, String date, String desc, String imagetitle, String urltriler, String imageposter) {
        this.urltriler = urltriler;
        txtTitle.setText(title);
        final String NEW_FORMAT = "dd MMMM, yyyy";
        String newDateString = date;
        final String OLD_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);

        try {
            Date d = sdf.parse(date);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {

        }
        txtDate.setText(newDateString);
        txtDesc.setText(desc);
        Glide.with(this)
                .load(imageposter)
                .into(imagePoster);
        Glide.with(this)
                .load(imagetitle)
                .into(imageTitle);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imageTitle:
                Intent intent = new Intent(getApplicationContext(), TrailerActivity.class);
                Bundle b = new Bundle();
                b.putString("url", urltriler);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
}
