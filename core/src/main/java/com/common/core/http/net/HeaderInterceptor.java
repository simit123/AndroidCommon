package com.common.core.http.net;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author by wuYang
 * @date 2019/9/2
 * @describe 添加公共请求头 header
 */
public class HeaderInterceptor implements Interceptor {
    private static final String TAG = "HeaderInterceptor";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        builder.header("token", "");//此处的token应该从存储的地方（sp）取出
        Request request = builder.method(original.method(), original.body()).build();
        return chain.proceed(request);
    }

}
