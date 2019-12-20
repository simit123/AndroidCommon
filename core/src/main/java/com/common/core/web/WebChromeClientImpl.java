package com.common.core.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe WebChromeClient 的具体实现
 */
public class WebChromeClientImpl extends WebChromeClient {

    //js弹窗的回调可在此处理
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
