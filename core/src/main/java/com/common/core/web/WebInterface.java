package com.common.core.web;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.common.core.web.event.Event;
import com.common.core.web.event.EventManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author by wuYang
 * @date 2019/10/15
 * @describe 注入进js的对象
 */
public class WebInterface {

    private static final String TAG = "WebInterface";
    private WebFragment mFragment;

    public WebInterface(WebFragment mFragment) {
        this.mFragment = mFragment;
    }

    public static WebInterface create(WebFragment mFragment) {
        return new WebInterface(mFragment);
    }

    @JavascriptInterface
    public String event(String params) {
        String action = "";
        try {
            JSONObject jsonObject = new JSONObject(params);
            action = jsonObject.optString("action");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "Event parse fail.");
        }
        final Event event = EventManager.getInstance().createEvent(action);
        event.setAction(action);
        event.setFragment(mFragment);
        return event.execute(params);
    }
}
