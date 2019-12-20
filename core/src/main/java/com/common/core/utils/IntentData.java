package com.common.core.utils;

/**
 * @author by wuYang
 * @date 2019/8/9
 * @describe intent 跳转实体类
 */
public class IntentData {
    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public IntentData(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
