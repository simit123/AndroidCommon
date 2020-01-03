package com.common.core.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.common.core.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author by wuYang
 * @date 2019/12/23
 * @describe
 */
public abstract class BaseActivity<P extends BasePresenter> extends AbstractBaseActivity implements IBaseView {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

    }

    public abstract P getP();

    /**
     * eventBus 使用时注册eventBus的类必须有一个添加了@Subscribe注解并且带参数的方法
     */
    @Subscribe
    public void onEvent(MessageEvent messageEvent) {
    }
}
