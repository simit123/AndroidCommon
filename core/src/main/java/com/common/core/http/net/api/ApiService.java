package com.common.core.http.net.api;

import com.common.core.http.net.bean.BaseResponse;
import com.common.core.http.net.bean.HomeDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe HTTP 接口定义
 */
public interface ApiService {
    @GET
    Observable<BaseResponse<HomeDataBean>> getHomeData(@Url String url);

}