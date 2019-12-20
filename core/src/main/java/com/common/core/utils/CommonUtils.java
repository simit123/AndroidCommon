package com.common.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.text.TextUtils;


import com.common.core.base.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 通用工具类
 */
public class CommonUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Toast工具
     * @param activity Activity
     * @param msg message
     */
//    public static void showMessage(Activity activity, String msg) {
//        Log.e("showMessage ：",msg);
//        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
//    }

//    /**
//     * Show message
//     *
//     * @param activity Activity
//     * @param msg message
//     */
//    public static void showSnackMessage(Activity activity, String msg) {
//        LogHelper.e("showSnackMessage ：" + msg);
//        //去掉虚拟按键
//        activity.getWindow().getDecorView().setSystemUiVisibility(
//                //隐藏虚拟按键栏 | 防止点击屏幕时,隐藏虚拟按键栏又弹了出来
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE
//        );
//        final Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
//        View view = snackbar.getView();
//        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, R.color.white));
//        snackbar.setAction("知道了", v -> {
//            snackbar.dismiss();
//            //隐藏SnackBar时记得恢复隐藏虚拟按键栏,不然屏幕底部会多出一块空白布局出来,很难看
//            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        }).show();
//        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
//            @Override
//            public void onDismissed(Snackbar transientBottomBar, int event) {
//                super.onDismissed(transientBottomBar, event);
//                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//            }
//        });
//    }

    /**
     * 判断2个对象是否相等
     *
     * @param a Object a
     * @param b Object b
     * @return isEqual
     */
    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T>    转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    /**
     * int 转 string
     *
     * @return
     */
    public static String intToString(int i) {
        return String.format(Locale.getDefault(), "%d", i);
    }

    public static String floatToString(float i) {
        return String.format(Locale.getDefault(), "%.1f", i);
    }


    /**
     * 获取日期 格式：2018-2-20
     */
    public static String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        Calendar current = Calendar.getInstance();// 获取当前时间
        return formatter.format(current.getTime());
    }

    /**
     * 获取星期 格式：星期一
     */
    public static String getStringWeek() {
        String mWay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        return "星期" + mWay;
    }


    /**
     * 根据天气code 匹配对应的天气图片 规则 weather + code
     *
     * @param code
     * @return
     */
    public static int getWeatherIcon(String code) {
        BaseApplication instance = BaseApplication.getInstance();
        return instance.getResources().getIdentifier("weather" + code, "mipmap", instance.getPackageName());
    }

    /**
     * byte数组按UTF-8转为String
     */
    public static String bytesToUTF_8(byte[] bArray) {
        try {
            return new String(bArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}