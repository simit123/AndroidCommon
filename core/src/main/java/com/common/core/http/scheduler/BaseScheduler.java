package com.common.core.http.scheduler;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 统一线程调度类
 */
public class BaseScheduler<T> implements ObservableTransformer<T,T> {
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;

    /**
     * @param subscribeOnScheduler 观察者所在线程
     * @param observeOnScheduler 被观察者所在线程
     */
    public BaseScheduler(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }
}
