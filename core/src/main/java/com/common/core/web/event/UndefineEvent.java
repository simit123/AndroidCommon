package com.common.core.web.event;

import android.util.Log;


public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e("UndefineEvent", params);
        return null;
    }
}
