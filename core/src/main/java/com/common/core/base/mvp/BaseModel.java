package com.common.core.base.mvp;

import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author by wuYang
 * @date 2019/9/5
 * @describe model基类 所有model必须继承此类 并且必须 调用createRequestBody方法
 */
public class BaseModel {
    private static final String TAG = "BaseModel";

    private BasePresenter mPresenter;

    public BaseModel(BasePresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public RequestBody createRequestBody(HashMap<Object, Object> params) {
        if (mPresenter == null) {
            throw new IllegalStateException("Presenter can not be null.");
        }
        mPresenter.checkViewAttach();
        String raw = new Gson().toJson(params);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
    }

    public RequestBody createRequestBody() {
        if (mPresenter == null) {
            throw new IllegalStateException("Presenter can not be null.");
        }
        mPresenter.checkViewAttach();
        String raw = new Gson().toJson(new HashMap<>());
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
    }
}
