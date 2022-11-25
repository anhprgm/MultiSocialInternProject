package com.theanhdev.multisocialprooject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class WebActivity extends AppCompatActivity {
    private ImageView returnBtn;
    private WebView webView;
    private String type = "";
    private ProgressBar progressBar;
    private TextView linkWeb;
    private boolean isOpenMess = false;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        binding();
        returnBtn.setOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            } else onBackPressed();
        });
        progressBar = findViewById(R.id.loadingBar);
        progressBar.setMax(100);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("type") != null) {
                type = bundle.getString("type");
            }
        }
        webView.getSettings().setJavaScriptEnabled(true);
        getUrl();
        loadWeb(type);
        setProgressBar();
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                WebView webView = (WebView) v;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
            }
            return false;
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }
    private void loadWeb(String type) {
        switch (type) {
            case "facebook":
                webView.loadUrl("https://www.facebook.com/");
                break;
            case "youtube":
                webView.loadUrl("https://www.youtube.com/");
                break;
            case "tiktok":
                webView.loadUrl("https://www.tiktok.com/");
                break;
            case "telegram":
                webView.loadUrl("https://web.telegram.org/k/");
                break;
        }

    }

    private void binding() {
        returnBtn = findViewById(R.id.return_btn);
        webView = findViewById(R.id.webView);
        linkWeb = findViewById(R.id.Link);
    }

    private void getUrl() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setLinkWeb(url);
                Log.d("Webview", url);
                String cutUrl = url.substring(0, 12);
//                if ("fb-messenger".equals(cutUrl))
//                    openMessenger();
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                setLinkWeb(url);
//                Log.d("Webview", url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onLoadResource(view, url);
            }


        });
    }
    private void setProgressBar() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setVisibility(View.VISIBLE);
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    private void setLinkWeb(String s) {
        linkWeb.setText(s);
    }
    private void openMessenger() {

    }
}