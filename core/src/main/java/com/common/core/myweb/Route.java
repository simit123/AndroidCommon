package com.common.core.myweb;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.WebView;

import com.common.core.utils.toast.ToastUtils;

/**
 * @author by wuYang
 * @date 2019/12/20
 * @describe
 */
public final class Route {

    private Route() {
    }

    private static class Holder {
        private static final Route INSTANCE = new Route();
    }

    public static Route getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 此方法统一处理js传过来的数据
     * 可在此通过原生的方式打开新的网页，只需要在此新建Fragment 或 activity
     *
     * @return false webView 自己处理
     */
    public boolean handleWebUrl(WebView webView, String url) {

        if (webView == null || url == null || url.isEmpty()) {
            return true;
        }
        if (url.contains(RouteKeys.PHONE_PROTOCOL)) {
            callPhone(webView.getContext(), url);
            return true;
        }

        // TODO: 2019/12/20 此处可以根据js传过来的协议判断调用哪个android方法
        Uri uri = Uri.parse(url);
        if (uri.getScheme().equals("js")) {
            if (uri.getAuthority().equals("webview")) {
                ToastUtils.show("js调用了android的方法");
            }
            return true;
        }
        return false;
    }

    private void callPhone(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(url));
        ContextCompat.startActivity(context, intent, null);

    }
}
