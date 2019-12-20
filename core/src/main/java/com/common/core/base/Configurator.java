package com.common.core.base;
import com.common.core.utils.LogUtils;
import com.common.core.utils.Preferences;
import com.common.core.utils.toast.ToastUtils;


/**
 * @author by wuYang
 * @date 2019/8/28
 * @describe application 配置
 */
public final class Configurator {

    private static final String TAG = "Configurator";

    private Configurator() {
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

//    public final Configurator withEventBus() {
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
//        return this;
//    }


    /**
     * 初始化Preferences
     *
     * @return
     */
    public final Configurator withPreferences() {
        Preferences.init(BaseApplication.getInstance());
        return this;
    }

    /**
     * 初始化Toast
     *
     * @return
     */
    public final Configurator withToast() {
        ToastUtils.init(BaseApplication.getInstance());
        return this;
    }

    /**
     * 初始化log工具
     *
     * @return
     */
    public final Configurator withLog() {
        //配置日志信息
        LogUtils.getInstance().setLogPath(Configs.LOG_PATH)
                .setLogName(Configs.LOG_NAME)
                .isOpen(Configs.LOG_ENABLE)
                .isSave(Configs.SAVE_ENABLE)
                .initialize();
        return this;
    }

    public final Configurator withPush() {
        initPush();
        return this;
    }


    private void initPush() {
//        //初始化push推送服务
//        if (shouldInit()) {
//            MiPushClient.registerPush(BaseApplication.getInstance(), Configs.APP_ID, Configs.APP_KEY);
//        }
//        //打开Log
//        LoggerInterface newLogger = new LoggerInterface() {
//
//            @Override
//            public void setTag(String tag) {
//                // ignore
//            }
//
//            @Override
//            public void log(String content, Throwable t) {
//                LogUtils.d(TAG, content);
//            }
//
//            @Override
//            public void log(String content) {
//                LogUtils.d(TAG, content);
//            }
//        };
//        Logger.setLogger(BaseApplication.getInstance(), newLogger);
    }

//    private boolean shouldInit() {
//        ActivityManager am = ((ActivityManager) BaseApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE));
//        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
//        String mainProcessName = BaseApplication.getInstance().getApplicationInfo().processName;
//        int myPid = Process.myPid();
//        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
//            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
