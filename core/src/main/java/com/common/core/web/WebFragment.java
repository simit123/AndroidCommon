package com.common.core.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.lang.ref.WeakReference;

/**
 * @author by wuYang
 * @date 2019/10/14
 * @describe
 */
public abstract class WebFragment extends Fragment implements IWebViewInitializer {

    private WebView mWebView = null;
    private boolean mIsWebViewAvailable = false;//webView的状态 是否可用
    private String mUrl = null;
    private Fragment topFragment;//栈顶的fragment
    private static final String JS_INTERFACE = "js_interface";//注入进web的对象名称

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUrl = arguments.getString(RouteKeys.WEB_URL);
        }
        initWebView();
    }

    public abstract IWebViewInitializer setInitializer();

    //处理跳转时的逻辑
    public abstract IUrlHandler setUrlHandler();

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

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
        mIsWebViewAvailable = false;
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

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()));
                mWebView = webViewWeakReference.get();
                initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                // TODO: 2019/10/14 添加js交互接口
                //将java对象注入到web
                mWebView.addJavascriptInterface(WebInterface.create(this),JS_INTERFACE);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }


    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null.");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("Url is null.");
        }
        return mUrl;
    }

    public Fragment getTopFragment() {
        if (topFragment == null) {
            topFragment = this;
        }
        return topFragment;
    }

    public void setTopFragment(Fragment topFragment) {
        this.topFragment = topFragment;
    }
}
