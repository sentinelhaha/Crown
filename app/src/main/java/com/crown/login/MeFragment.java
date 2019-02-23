package com.crown.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zjj on 2018/11/26.
 */

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //把fragment_index.xml转成view对象
        View view=inflater.inflate( R.layout.fragment_me,null);
        //找到webview
        WebView webView= (WebView) view.findViewById(R.id.webView);
        //让webView加载网页
       /*String url=PdApplication.SERVER_IP+"/pdIndex.html";*/
        String url=PdApplication.SERVER_IP+"/personal/";
        /*String url="http://127.0.0.1:8092/index.html";*/

        webView.loadUrl(url);
        //加webClient后，单击超连接，不会跳出app
        webView.setWebViewClient(new WebViewClient());
        //得到设置对象
        WebSettings webSettings=webView.getSettings();

        //用设置对象设置webView能运行js
        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}
