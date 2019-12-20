package com.common.core.http.net;


import com.common.core.base.Constants;
import com.common.core.http.net.api.ApiService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by wuYang
 * @date 2019/8/30
 * @describe retrofit 配置 没有token
 */
public class RetrofitCreatorWithoutToken {

    public static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static class OkHttpClientHolder {
        private final static int TIMEOUT = 15;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        private static OkHttpClient.Builder addInterceptors() {
            final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            INTERCEPTORS.add(httpLoggingInterceptor);
            for (Interceptor interceptor : INTERCEPTORS) {
                BUILDER.addInterceptor(interceptor);
            }
            return BUILDER;
        }
    }


    private static class RetrofitHolder {
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiService getService() {
        return ServiceHolder.SERVICE;
    }

    private static class ServiceHolder {
        private static final ApiService SERVICE = RetrofitHolder.RETROFIT.create(ApiService.class);
    }
}
