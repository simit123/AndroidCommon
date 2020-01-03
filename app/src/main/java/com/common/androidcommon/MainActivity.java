package com.common.androidcommon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.common.core.base.mvp.BaseActivity;
import com.common.core.base.mvp.BasePresenter;
import com.common.core.myweb.ExampleWebFragment;
import com.common.core.utils.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bt)
    public Button bt;


    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        ExampleWebFragment baseWebFragment = ExampleWebFragment.newInstance("file:///android_asset/js2.html");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, baseWebFragment);
        ft.commit();

    }

    @Override
    public BasePresenter getP() {
        return null;
    }


    @OnClick(R.id.bt)
    public void onViewClicked() {
        ToastUtils.show("点击button");
    }
}
