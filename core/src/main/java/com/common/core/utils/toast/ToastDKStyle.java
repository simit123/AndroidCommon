package com.common.core.utils.toast;

import android.content.Context;


/**
 * 金质空气toast样式
 */
public class ToastDKStyle extends BaseToastStyle {

    public ToastDKStyle(Context context) {
        super(context);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(1000);
    }

    @Override
    public int getBackgroundColor() {
        return 0XFFFFFFFF;
    }

    @Override
    public int getTextColor() {
        return 0xff2a8fa0;
    }

    @Override
    public float getTextSize() {
        return sp2px(15);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(24);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(8);
    }

    @Override
    public int getPaddingBottom() {
        return dp2px(8);
    }
}