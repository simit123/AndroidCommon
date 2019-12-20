package com.common.core.http.net;

import android.text.TextUtils;

import com.common.core.R;
import com.common.core.base.BaseApplication;
import com.common.core.base.mvp.IBaseView;
import com.common.core.utils.LogUtils;
import com.common.core.views.Loader;

import io.reactivex.observers.ResourceObserver;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.HttpException;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 封装的观察者，用于集中处理一些网络错误相关的东西
 */
public abstract class BaseObserver<T> extends ResourceObserver<T> {
    private static final String TAG = "BaseObserver";

    private IBaseView mView;
    private String mErrorMsg;//暴漏给用户的请求错误提示
    private boolean isShowError = true;//是否展示点击重新加载的UI
    private boolean isShowLoading = true;//是否展示loading 默认展示

    public BaseObserver(IBaseView view) {
        this.mView = view;
    }

    protected BaseObserver(IBaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(IBaseView view, boolean isShowError) {
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(IBaseView view, String errorMsg, boolean isShowError) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }
    protected BaseObserver(boolean isShowLoading,IBaseView view) {
        this.mView = view;
        this.isShowLoading = isShowLoading;
    }


    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "请求开始");
        showLoading();
    }

    @Override
    public void onComplete() {
        LogUtils.i(TAG, "请求结束");
        stopLoading();
    }

    @Override
    public void onNext(T t) {
        LogUtils.i(TAG, "请求成功");
        stopLoading();

    }

    @Override
    public void onError(Throwable e) {
        LogUtils.i(TAG, "请求错误");
        stopLoading();

        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(BaseApplication.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(BaseApplication.getInstance().getString(R.string.unKnown_error));
        }
        if (isShowError) {
            mView.showError();
        }
    }

    private void showLoading() {
        if (!isShowLoading) {
            return;
        }
        if (mView == null) {
            LogUtils.e(TAG,"mView can not be null.");
            return;
        }
        if (mView instanceof SupportActivity) {
            Loader.showLoading(((SupportActivity) mView));
        } else if (mView instanceof SupportFragment) {
            Loader.showLoading((((SupportFragment) mView).getActivity()));
        } else {
            throw new IllegalArgumentException("Loader arguments must be context!");
        }
    }

    private void stopLoading() {
        if (!isShowLoading) {
            return;
        }
        Loader.dismissLoading();
    }
}
