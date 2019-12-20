package com.common.core.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by kk on 2019/8/20 11:37
 */
public abstract class BaseFragment<P extends BasePresenter> extends SupportFragment implements IBaseView {

    private Unbinder unBinder;
    public P mPresenter;
    private FrameLayout fl_container;//使用FrameLayout 防止后期有可能添加多层级UI


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fl_container == null) { //配合viewpager使用时  有时滑动fragment会重新初始化 但布局view依然存在 会导致滑动回页面fragment界面数据消失
            fl_container = new FrameLayout(getContext());
            View rootView = inflater.inflate(getLayoutId(), container, false);
            fl_container.addView(rootView);
        }
        return fl_container;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        unBinder = ButterKnife.bind(this, view);
        initView(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public abstract int getLayoutId();

    public abstract P getP();

    public abstract void initData();

    public abstract void initView(Bundle savedInstanceState);

}
