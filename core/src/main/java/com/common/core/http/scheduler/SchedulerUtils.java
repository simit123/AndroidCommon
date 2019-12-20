package com.common.core.http.scheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 线程调度工具
 */
public class SchedulerUtils {

    public  static <T> IoMainScheduler<T> ioToMain(){
        return new IoMainScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
