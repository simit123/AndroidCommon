package com.common.core.base.mvp;

/**
 *  view 接口的基类 统一的view ui 处理
 */
public interface IBaseView {
    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    /**
     * Show error
     */
    void showError();
}
