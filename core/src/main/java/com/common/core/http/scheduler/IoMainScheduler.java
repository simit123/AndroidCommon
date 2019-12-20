package com.common.core.http.scheduler;

import io.reactivex.Scheduler;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 主线程调度
 */
public class IoMainScheduler<T> extends BaseScheduler<T> {

    /**
     * @param subscribeOnScheduler 观察者所在线程
     * @param observeOnScheduler   被观察者所在线程
     */
    public <T> IoMainScheduler(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        super(subscribeOnScheduler, observeOnScheduler);
    }
}
