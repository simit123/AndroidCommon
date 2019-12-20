package com.common.core.http.net;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 统一服务器异常处理
 */
public class ServerException extends Exception {

    private int code;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
