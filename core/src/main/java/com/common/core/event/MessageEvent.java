package com.common.core.event;

import java.util.HashMap;

public class MessageEvent {
    public String message;//事件类型
    private HashMap<String, Object> bean = new HashMap<>();//事件内容

    public Object getBean() {
        return bean;
    }

    public void put(String key, Object value) {
        bean.put(key, value);
    }

    public Object get(String key) {
        return bean.get(key);
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
