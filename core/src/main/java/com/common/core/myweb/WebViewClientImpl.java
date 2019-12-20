package com.common.core.myweb;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.common.core.utils.LogUtils;

/**
 * @author by wuYang
 * @date 2019/12/17
 * @describe WebViewClient的具体是实现
 * 功能：
 * 设置webView加载loading
 * 通过shouldOverrideUrlLoading(),拦截js事件
 */
public class WebViewClientImpl extends WebViewClient {
    private static final String TAG = "WebViewClientImpl";

    private IPageLoadListener iPageLoadListener;

    public void setPageLoadListener(IPageLoadListener iPageLoadListener) {
        this.iPageLoadListener = iPageLoadListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //拦截Url处理js传递过来的消息
        return Route.getInstance().handleWebUrl(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (iPageLoadListener != null) {
            //将事件回调给调用者处理
            iPageLoadListener.onLoadStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (iPageLoadListener != null) {
            iPageLoadListener.onLoadEnd();
        }

    }
}
