package com.common.core.web;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe WebViewClient的具体是实现
 */
public class WebViewClientImpl extends WebViewClient {


    private WebFragment mFragment;
    private IPageLoadListener pageLoadListener;

    public WebViewClientImpl(WebFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.pageLoadListener = pageLoadListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //此处用原生的方式模拟web页面跳转并进行页面加载
        // TODO: 2019/10/14 考虑初次加载是否会重复跳转fragment
        return Route.getInstance().handleWebUrl(mFragment, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (pageLoadListener != null) {
            pageLoadListener.onLoadStart();
        }
        // TODO: 2019/10/14 展示loading
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (pageLoadListener != null) {
            pageLoadListener.onLoadEnd();
        }
        // TODO: 2019/10/14 消除loading
    }
}
