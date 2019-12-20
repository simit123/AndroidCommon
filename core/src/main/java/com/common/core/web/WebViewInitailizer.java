package com.common.core.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe
 */
public class WebViewInitailizer {

    private final static String USER_AGENT = "com.android.application";

    @SuppressLint("SetJavaScriptEnabled")
    public static WebView createView(WebView webView) {
        //允许调试webView加载的任何内容
        WebView.setWebContentsDebuggingEnabled(true);

        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏幕长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        //初始化webSettings
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置app user-agent 特征标识
        String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + USER_AGENT);
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;


    }
}
