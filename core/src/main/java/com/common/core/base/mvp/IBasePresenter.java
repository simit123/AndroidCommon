package com.common.core.base.mvp;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe presenter 的基类，泛型参数为 IBaseView 的子类
 */
public interface IBasePresenter<T extends IBaseView> {

    void attachView(T mRootView);
    void detachView();
}
