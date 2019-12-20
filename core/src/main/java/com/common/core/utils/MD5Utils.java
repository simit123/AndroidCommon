package com.common.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 */
public class MD5Utils {

    private static final String TAG = "MD5Utils";

    /**
     * md5加密
     *
     * @param plainText 待加密字符串
     * @return 加密后32位字符串
     */
    public static String encrypt(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e(TAG, "getMd5 error" + e);
            return null;
        }
    }

    /**
     * 获取MD5加密后的byte数据
     *
     * @param s
     * @return
     */
    public static byte[] getMD5Byte(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}