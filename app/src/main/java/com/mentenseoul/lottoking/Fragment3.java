package com.mentenseoul.lottoking;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    WebView webView;
    WebSettings webSettings;
        
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment3, container,  false);

        TextView textView = rootView.findViewById(R.id.textView);

        webView = rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                textView.setVisibility(textView.GONE);
            }
        });


        webSettings = webView.getSettings();
        webView.setBackgroundColor(Color.parseColor("#feffdf"));
        webSettings.setJavaScriptEnabled(true);



        webView.loadUrl("https://dhlottery.co.kr/common.do?method=main");

        return rootView;
    }

}
