package com.common.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * 字节工具类
 */
public class ByteUtils {

    public static byte[] numberToBytes(long number, int byteSize) {
        byte[] b = new byte[byteSize];
        for (int i = byteSize - 1; i >= 0; i--) {
            b[i] = (byte) (number >> (8 * (byteSize - 1 - i)) & 0xff);
        }
        return b;
    }

    public static long bytesToUnsignedInt(byte[] b) {
        return (b[3] & 0xff) | ((long) (b[2] & 0xff)) << 8 | ((long) (b[1] & 0xff)) << 16 | ((long) (b[0] & 0xff)) << 24;
    }

    public static int bytesToUnsignedShort(byte[] b) {
        return b[1] & 0xff | (b[0] & 0xff) << 8;
    }

    public static int bytesToUnsignedByte(byte[] b) {
        return b[0] & 0xff;
    }

    public static void printHexString(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            LogUtils.i("liu", hex.toUpperCase());
        }

    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            return null;
        }
        return bytes;
    }

    /**
     * 判断前4位是否相等
     *
     * @param heartbeats
     * @param resultByte
     * @return
     */
    public static boolean isSameByte(byte[] heartbeats, byte[] resultByte) {
        if(resultByte==null){
            return false;
        }
        byte[] array = Arrays.copyOf(resultByte, 4);
        return Arrays.equals(heartbeats, array);
    }
}