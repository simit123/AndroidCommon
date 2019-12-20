package com.common.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.common.core.base.BaseApplication;


/**
 * @author by wuYang
 * @date 2019/8/28
 * @describe 屏幕辅助类
 */
public class DimenUtil {

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWith() {
        final Resources resources = BaseApplication.getInstance().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        final Resources resources = BaseApplication.getInstance().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
