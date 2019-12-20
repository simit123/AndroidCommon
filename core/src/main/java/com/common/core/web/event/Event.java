package com.common.core.web.event;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.webkit.WebView;

import com.common.core.web.WebFragment;

/**
 * @author by wuYang
 * @date 2019/10/15
 * @describe
 */
public abstract class Event implements IEvent {
    private String mAction = null;
    private WebFragment mFragment = null;

    public Context getContext() {
        return mFragment.getContext();
    }

    public WebView getWebView() {
        return mFragment.getWebView();
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(WebFragment mDelegate) {
        this.mFragment = mDelegate;
    }

    public String getUrl() {
        return mFragment.getUrl();
    }
}
