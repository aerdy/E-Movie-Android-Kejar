package com.androidkerjar.anjarmoview.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidkerjar.anjarmoview.R;

/**
 * Created by Jarcode on 2016-04-16.
 */
public class TrailerActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        Bundle b = getIntent().getExtras();
        final String urltriler = b.getString("url");
        Log.e("url", urltriler);

        webView = (WebView) findViewById(R.id.webview);
        final ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(urltriler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            webView.destroy();
        } catch (Exception e) {

        }
    }
}
