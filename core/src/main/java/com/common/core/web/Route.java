package com.common.core.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe webView 的页面跳转和加载逻辑
 */
public class Route {

    private Route() {
    }

    private static class Holder {
        private static final Route INSTANCE = new Route();
    }

    public static Route getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 原生的方式模拟web页面跳转并进行页面加载
     *
     * @param currentFragment
     * @param nextFragment
     * @param url
     * @return
     */
    public boolean handleWebUrl(WebFragment currentFragment, WebFragment nextFragment, String url) {

        //如果是电话协议
        if (url.contains(RouteKeys.PHONE_PROTOCOL)) {
            callPhone(currentFragment.getContext(), url);
            return true;
        }
        Fragment topFragment = currentFragment.getTopFragment();

        // TODO: 2019/10/14 此处启动nextFragment 跳转下一个页面 也可换成activity
//        topFragment.start(nextFragment);

        return true;
    }


    public boolean handleWebUrl(WebFragment currentFragment, String url) {
        return handleWebUrl(currentFragment, WebFragmentImpl.create(url), url);
    }

    public final void loadPage(WebFragment webFragment, String url) {
//        loadWebPage(webFragment.getWebView(), url);
        loadPage(webFragment.getWebView(), url);
    }


    /**
     * 加载网络web
     *
     * @param webView
     * @param url
     */
    private void loadWebPage(WebView webView, String url) {
        if (webView == null || url == null) {
            throw new NullPointerException("WebView or Url is null.");
        }
        webView.loadUrl(url);
    }

    /**
     * 加载本地web
     *
     * @param webView
     * @param url
     */
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }


    private void loadPage(WebView webView, String url) {
//        if (URLUtil.isAssetUrl(url)) {
//            loadLocalPage(webView, url);
//        }
        loadWebPage(webView, url);
    }


    /**
     * 打电话
     *
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
