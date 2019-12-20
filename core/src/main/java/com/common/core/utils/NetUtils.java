package com.common.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class NetUtils {
    private static NetUtils instance = null;
    private static final int NETWORK_NONE = 1;
    private static final int NETWORK_MOBILE = 2;
    private static final int NETWORK_WIFI = 3;


    public static NetUtils getInstance() {
        if (instance == null) {
            instance = new NetUtils();
        }
        return instance;
    }

    // 是否有网络链接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            return (info != null && info.isAvailable());
        }
        return false;
    }

    // wifi是否可用
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    // mobel网络是否可用
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    public boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     *
     * @return
     */
    public boolean ping() {
//        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
            // 读取ping的内容，可以不加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content;
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
//            Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状态
            int status = p.waitFor();
            if (status == 0) {
//                result = "success";
                return true;
            } else {
//                result = "failed";
            }
        } catch (IOException e) {
//            result = "IOException";
        } catch (InterruptedException e) {
//            result = "InterruptedException";
        } finally {
//            Log.d("----result---", "result = " + result);
        }
        return false;
    }

    public boolean isNetWorkAvailableOfDNS() {
        boolean have = false;
        try {
            DNSParse parse = new DNSParse("www.baidu.com");
            Thread thread = new Thread(parse);
            thread.start();
            thread.join(3 * 1000); // 设置等待DNS解析线程响应时间为3秒
            InetAddress resCode = parse.get(); // 获取解析到的IP地址
            if (resCode != null) {
                have = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            have = false;
        }

        return have;
    }

    /**
     * DNS解析线程
     */
    private class DNSParse implements Runnable {
        private String hostname;
        private InetAddress address;

        public DNSParse(String hostname) {
            this.hostname = hostname;
        }

        public void run() {
            try {
                set(InetAddress.getByName(hostname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public synchronized void set(InetAddress address) {
            this.address = address;
        }

        public synchronized InetAddress get() {
            return address;
        }
    }

    public  int getNetWorkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (networkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        }
        return NETWORK_NONE;
    }
}
