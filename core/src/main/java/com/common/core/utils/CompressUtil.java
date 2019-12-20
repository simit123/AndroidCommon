package com.common.core.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author by chencz
 * @date 2019/9/23
 * @describe compress bitmap
 */
public class CompressUtil {

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        //大于2M压缩（需求是这样）
        while (baos.toByteArray().length / 1024 >2000) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options = options == 10 ? options : options - 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 尺寸压缩
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap sizeComoress(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;
        float ww = 480f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        // be=1表示不缩放
        int be = 1;
        // 如果宽度大的话根据宽度固定大小缩放
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);
    }


    /**
     * 根据图片路径压缩图片并返回压缩后图片路径
     *
     * @param path
     * @return
     */
    public static byte[] compress(String path) {
        Bitmap bitmap = sizeComoress(path);
        if (bitmap == null) {
            return null;
        }
        //读取照片旋转角度
        int degree = readPictureDegree(path);
        if (degree > 0) {
            //旋转照片
            bitmap = rotateBitmap(bitmap, degree);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }


    /**
     * 读取照片的旋转角度
     *
     * @param path
     * @return
     */
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return degree;
    }

    /**
     * 旋转照片
     *
     * @param bitmap
     * @param rotate
     * @return
     */
    private static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }
}
