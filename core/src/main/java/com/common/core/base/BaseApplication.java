package com.common.core.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @author by wuYang
 * @date 2019/8/14
 * @describe application 父类
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public synchronized static BaseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * application 基本配置
     */
    private void init() {
        Configurator.getInstance()
                .withPreferences()
                .withLog()
                .withToast();
    }
}
