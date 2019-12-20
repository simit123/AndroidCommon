package com.common.core.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.common.core.R;
import com.common.core.utils.ActivityCollector;
import com.common.core.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 处理butterKnife的绑定等统一处理
 */
public abstract class AbstractBaseActivity extends SupportActivity {


    private Unbinder unBinder;
    private static final String TAG = "AbstractBaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityCollector.getInstance().addActivity(this);
        //沉浸式状态栏的配置
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, R.color.status_bar_default_color);
        }
        unBinder = ButterKnife.bind(this);
        //        initToolbar();
        onViewCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();//
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.base_layout);
        FrameLayout root = (FrameLayout) findViewById(R.id.root);
        getLayoutInflater().inflate(layoutResID, root, true);
    }

    /**
     * 在initData()之前执行
     */
    protected abstract void onViewCreated();

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

//    /**
//     * 初始化ToolBar
//     */
//    protected abstract void initToolbar();

    /**
     * 初始化数据 会在onResume处调用 默认实现此方法，所有业务相关逻辑可在此处理
     */
    protected abstract void initData();
}
