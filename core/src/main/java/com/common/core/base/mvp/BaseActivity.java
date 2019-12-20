package com.common.core.base.mvp;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.common.core.utils.NetWorkStateReceiver;
import com.common.core.utils.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by kk on 2019/8/20 11:21
 */
public abstract class BaseActivity<P extends BasePresenter> extends AbstractBaseActivity implements IBaseView {

    private static final String TAG = "BaseActivity";

    public P mPresenter;

    private NetWorkStateReceiver netWorkStateReceiver;


    @SuppressWarnings("unchecked")
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
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
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
    protected void attachBaseContext(Context newBase) {
        // TODO: 2019/12/9 多语言适配
//        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtils.show(errorMsg);
    }

    @Override
    public void showError() {

    }

    /**
     * 默认重写此方法，不用mvp返回null即可
     *
     * @return
     */
    public abstract P getP();

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent() {
//
//    }
}
