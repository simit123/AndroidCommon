package com.common.core.utils;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * @author by wuYang
 * @date 2019/8/9
 * @describe Activity 跳转工具类
 */
public final class IntentUtils {
    public static void goActivity(Activity context, Class<?> clazz) {
        goActivity(context, clazz, new IntentData[0]);
    }

    /**
     * intent 跳转
     *
     * @param context
     * @param clazz
     * @param intentData
     */
    public static void goActivity(Activity context, Class<?> clazz, IntentData... intentData) {
        Intent intent = new Intent(context, clazz);
        for (IntentData data : intentData) {
            String key = data.getKey();
            Object value = data.getValue();
            putExtra(intent, key, value);
        }
        context.startActivity(intent);
    }

    private static void putExtra(Intent intent, String key, Object value) {
        if (value instanceof String) {
            intent.putExtra(key, (String) value);
        }
        if (value instanceof Boolean) {
            intent.putExtra(key, (boolean) value);
        }
        if (value instanceof Integer) {
            intent.putExtra(key, (int) value);
        }
        if (value instanceof Float) {
            intent.putExtra(key, (float) value);
        }
        if (value instanceof Double) {
            intent.putExtra(key, (double) value);
        }
        if (value instanceof Byte) {
            intent.putExtra(key, (byte) value);
        }
        if (value instanceof Serializable) {
            intent.putExtra(key, (Serializable) value);
        }
    }
}
