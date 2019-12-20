package com.common.core.myweb;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author by wuYang
 * @date 2019/12/17
 * @describe webView的基础配置
 */
public class WebViewInitialize {

    @SuppressLint("SetJavaScriptEnabled")
    public static WebView initWebSettings(WebView webView) {
        //允许调试webView加载的任何内容
        WebView.setWebContentsDebuggingEnabled(true);
        //去掉横向滚动条
        webView.setHorizontalScrollBarEnabled(false);
        //去掉纵向滚动条
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //禁止长按事件
        webView.setOnLongClickListener(v -> true);
        //初始化webSettings
        final WebSettings settings = webView.getSettings();
        //允许与js交互
        settings.setJavaScriptEnabled(true);
        //隐藏webView缩放按钮
        settings.setDisplayZoomControls(false);
        //隐藏webView缩放工具
        settings.setBuiltInZoomControls(false);
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
