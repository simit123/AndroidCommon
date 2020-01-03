package com.common.core.myweb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;

import com.common.core.views.Loader;


/**
 * @author by wuYang
 * @date 2019/12/17
 * @describe webView 的fragment基类
 */
public abstract class BaseWebFragment extends Fragment implements IPageLoadListener {


    private WebView mWebView = null;
    private boolean mIsWebViewAvailable = false;//webView的状态 是否可用
    private String url;
    private boolean isNoLoading;//是否需要loading 默认需要


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initWebView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView != null) {
            mIsWebViewAvailable = false;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }

    /**
     * 获取当前webView对象
     *
     * @return webView对象
     */
    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null.");
        }
        if (!mIsWebViewAvailable) {
            throw new IllegalStateException("WebView is not available!");
        }
        return mWebView;
    }

    private void initWebView() {

        url = getArguments().getString(RouteKeys.WEB_URL);
        isNoLoading = getArguments().getBoolean(RouteKeys.NO_LOAD);
        mWebView = WebViewInitialize.initWebSettings(new WebView(getContext()));
        WebViewClientImpl webViewClient = new WebViewClientImpl();

        webViewClient.setPageLoadListener(this);
        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(new WebChromeClientImpl());
        //将Java对象注入进js
        mWebView.addJavascriptInterface(WebInterface.create(), RouteKeys.JS_INTERFACE);
        mIsWebViewAvailable = true;

        loadWebPage(url);

    }

    /**
     * 加载网络web
     *
     * @param url web地址
     */
    public void loadWebPage(String url) {
        if (mWebView == null || url == null || "".equals(url)) {
            throw new NullPointerException("WebView or Url is null.");
        }
        mWebView.loadUrl(url);
    }

    /**
     * 加载本地web
     *
     * @param url web地址
     */
    public void loadLocalPage(String url) {
        if (mWebView == null || url == null || "".equals(url)) {
            throw new NullPointerException("WebView or Url is null.");
        }
        loadWebPage("file:///android_asset/" + url);
    }

    @Override
    public void onLoadStart() {
        if (!isNoLoading) {
            Loader.showLoading(getContext());
        }
    }

    @Override
    public void onLoadEnd() {
        if (!isNoLoading) {
            Loader.dismissLoading();
        }
    }
}
