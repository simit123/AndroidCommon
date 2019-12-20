package com.common.androidcommon;

import android.webkit.JavascriptInterface;

import com.common.core.utils.toast.ToastUtils;

/**
 * @author by wuYang
 * @date 2019/12/16
 * @describe
 */
public class AndroidToJS extends Object {

    @JavascriptInterface
    public void hello(String msg) {
        ToastUtils.show(msg);
    }

}
