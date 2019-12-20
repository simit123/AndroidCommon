package com.common.core.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe webFragment 的具体实现
 */
public class WebFragmentImpl extends WebFragment implements IUrlHandler {


    private IPageLoadListener mPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener mPageLoadListener) {
        this.mPageLoadListener = mPageLoadListener;
    }

    public static WebFragmentImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.WEB_URL, url);
        WebFragmentImpl webFragmentImpl = new WebFragmentImpl();
        webFragmentImpl.setArguments(args);
        return webFragmentImpl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getWebView();
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public IUrlHandler setUrlHandler() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return WebViewInitailizer.createView(webView);
    }

    /***
     * 1、WebViewClient就是帮助WebView处理各种通知、请求事件的，具体来说包括：onLoadResource 、onPageStart 、onPageFinish 、onReceiveError 、onReceivedHttpAuthRequest
     * 2、WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
     */
    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setPageLoadListener(mPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public void handleUrl(WebFragment webFragment) {
        if (getUrl() != null) {
            //加载webView页面
            Route.getInstance().loadPage(webFragment, getUrl());
        }
    }
}
