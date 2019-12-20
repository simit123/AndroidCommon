package com.common.core.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * @author by wuYang
 * @date 2019/8/7
 * @describe 监听网络状态变化的广播
 */
public class NetWorkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetWorkStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (!wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                LogUtils.i(TAG, "网络连接已断开");
            } else {
                LogUtils.i(TAG, "网络已连接");
            }
        } else {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean isConnect = false;
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                if (networkInfo.isConnected()) {
                    isConnect = true;
                    break;
                }
            }
            if (!isConnect) {
                LogUtils.i(TAG, "网络连接已断开");
            } else {
                LogUtils.i(TAG, "网络已连接");
            }
        }
    }
}
