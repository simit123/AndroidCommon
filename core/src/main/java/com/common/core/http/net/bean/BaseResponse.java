package com.common.core.http.net.bean;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe
 */
public class BaseResponse<T> {

    /**
     * 0：成功，1：失败
     */
    private int code;

    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
