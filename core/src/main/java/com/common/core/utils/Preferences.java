package com.common.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe sharePreferences 工具类
 */
public class Preferences {
    private static final String PREFS_NAME = "PrefsFile";

    private static Preferences instance = null;

    private Context mContext;

    private SharedPreferences sp;

    public static void init(Context mContext) {
        if (instance == null) {
            instance = new Preferences(mContext);
        }
    }

    public static Preferences getInstance() {
        return instance;
    }

    private Preferences(Context mContext) {
        this.mContext = mContext;
        sp = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.apply();
    }
}