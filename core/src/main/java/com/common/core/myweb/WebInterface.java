package com.common.core.myweb;

import android.webkit.JavascriptInterface;

/**
 * @author by wuYang
 * @date 2019/12/17
 * @describe 注入进js的对象
 */
public class WebInterface {

    public static WebInterface create() {
        return new WebInterface();
    }


    @JavascriptInterface
    public void event(String action) {
        // TODO: 2019/12/17 此处定义的是js调用的Android方法，通过js传过来的action决定调用哪个方法
    }

}
