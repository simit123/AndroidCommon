package com.common.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.common.core.R;

import java.util.ArrayList;


/**
 * @author by wuYang
 * @date 2019/8/28
 * @describe loading 工具
 */
public class Loader {

    private static final int LOAD_SIZE_SCALE = 8;//loading 根据屏幕大小缩放比
    private static final int LOAD_OFFSET_SCALE = 10;//loading 偏移量

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();


    public static void showLoading(Context context, String msg) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View v = inflater.inflate(R.layout.loading_layout, null);// 得到加载view
        TextView tipTextView = v.findViewById(R.id.tv_tip);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        dialog.setContentView(v);
        dialog.setCancelable(false); //点击返回键loading不消失
        dialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域loading不消失
        LOADERS.add(dialog);
        dialog.show();

    }

    public static void showLoading(Context context) {
        showLoading(context, null);
    }


    public static void dismissLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        }
        LOADERS.clear();
    }


}
