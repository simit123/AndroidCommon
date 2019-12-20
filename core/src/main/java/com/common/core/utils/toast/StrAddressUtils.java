package com.common.core.utils.toast;

import android.text.TextUtils;

/**
 * @author by chencz
 * @date 2019/9/26
 * @describe
 */
public class StrAddressUtils {
    /**
     * @param province    省
     * @param city        市
     * @param currentArea 区
     * @param strInterval 拼接省市区的间隔符 例 省-市-区 或  省·市·区
     * @param length      是否展示全部文本（0：展示全部文本;>0：展示length位文本，超出以...展示）
     * @return
     */
    public static String getAddress(String province, String city, String currentArea, String strInterval, int length) {

        String strAddress = "";
        if (TextUtils.isEmpty(province)) {
            province = "";
        }
        strAddress += province;
        if (TextUtils.isEmpty(city) || city.equals(province)) {
            city = "";
        } else {
            strAddress += strInterval;
        }
        strAddress += city;

        if (TextUtils.isEmpty(currentArea) || currentArea.equals(province)) {
            currentArea = "";
        } else {
            strAddress += strInterval;
        }
        strAddress += currentArea;

//        String strAddress = (TextUtils.isEmpty(province) ? "" : province)
//                + (city.equals(province) ? (currentArea.equals(city) ? "" : strInterval + currentArea)
//                : city + strInterval + currentArea);
        return length == 0 ? strAddress : (strAddress.length() > length ? strAddress.substring(0, length) + "..." : strAddress);
    }
}
