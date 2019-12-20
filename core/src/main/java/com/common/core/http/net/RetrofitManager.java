package com.common.core.http.net;


import com.common.core.http.net.api.ApiService;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe Retrofit网络请求统一配置类
 */
public class RetrofitManager {

    private RetrofitManager() {
    }

    public static ApiService getService() {
        return RetrofitCreator.getService();
    }

    /**
     * 当一个请求不需要token时,调用此方法
     * @return
     */
    public static ApiService getServiceWithoutToken() {
        return RetrofitCreatorWithoutToken.getService();
    }

}
