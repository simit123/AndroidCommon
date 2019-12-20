package com.common.core.base;

import android.annotation.SuppressLint;

/**
 * @author by wuYang
 * @date 2019/8/13
 * @describe 相关常量配置
 */
public class Configs {

    //日志相关
    @SuppressLint("SdCardPath")
    public static final String LOG_PATH = "/sdcard/daikin/log";//log 保存路径
    public static final String LOG_NAME = "log.txt";//log文件名
    public static final boolean LOG_ENABLE = true;//是否打印log
    public static final boolean SAVE_ENABLE = false;//是否保存log

    //小米push相关
    public static final String APP_ID = "your appid";
    public static final String APP_KEY = "your appkey";
}
