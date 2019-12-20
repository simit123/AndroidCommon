package com.common.core.base.mvp;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe  presenter的基类 用于获取view对象 并处理 View 没有attach 可能造成的空指针异常 和 Rxjava 数据流调用过程中导致内存泄漏 （一种情况是 网络请求过程中 Activity由于某种情况而被销毁，rxjava的订阅没有得到及时的释放）
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {

    public T mRootView = null;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(T mRootView) {
        this.mRootView = mRootView;
    }

    @Override
    public void detachView() {
        mRootView = null;
        //取消rxjava的所有订阅
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    private boolean isViewAttached(){
        return mRootView != null;
    }

    public void addSubscription(Disposable disposable ) {
        compositeDisposable.add(disposable);
    }
    /**
     * 每次请求数据之前手动调用此方法检验View 是否Attached
     */
    public void checkViewAttach(){
        if (!isViewAttached()) {
            throw new RuntimeException("Please call IPresenter.attachView(IBaseView) before\" + \" requesting data to the IPresenter");
        }
    }



}
