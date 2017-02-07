package com.blink.dagger.gank.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by blink_dagger on 17-2-7.
 *
 * 颜色矩阵(ColorMatrix)实现滤镜效果
 一，知识简介
 一张位图可以转换为一个5*4的矩阵，涉及到颜色和透明度。
 颜色矩阵M是以一维数组m=[a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t]的方式进行存储的。
 在一张图片中，图像的RGBA（红色、绿色、蓝色、透明度）值决定了该图片所呈现出来的颜色效果。
 要想改变一张图片的颜色效果，只需要改变图像的颜色分量矩阵即可。通过颜色矩阵可以很方便的修改图像的颜色分量矩阵。
 由此可见，通过颜色矩阵修改了原图像的RGBA值，从而达到了改变图片颜色效果的目的。
 第一行参数abcde决定了图像的红色成分，
 第二行参数fghij决定了图像的绿色成分，
 第三行参数klmno决定了图像的蓝色成分，
 第四行参数pqrst决定了图像的透明度，
 第五列参数ejot是颜色的偏移量。
 */
public class ColorMatrixUtil {

    // 黑白效果矩阵
    public static Bitmap changeToGray(Bitmap bitmap) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //float[] array = {1, 0, 0, 0, 100,0, 1, 0, 0, 100, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0};
        //ColorMatrix colorMatrix = new ColorMatrix(array);

        //二，把饱和度设置为0 就可以得到灰色（黑白)的图片
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }

    // 怀旧效果矩阵
    public static Bitmap changeToOld(Bitmap bitmap) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float[] array = {0.393f,0.769f,0.189f,0,0,
                0.349f,0.686f,0.168f,0,0,
                0.272f,0.534f,0.131f,0,0,
                0,0,0,1,0};
        ColorMatrix colorMatrix = new ColorMatrix(array);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }

    // 宝丽来效果矩阵
    public static Bitmap changeToPolaroid(Bitmap bitmap) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float[] array = {1.438f, -0.062f, -0.062f, 0, 0,
                -0.122f, 1.378f, -0.122f, 0, 0,
                -0.016f, -0.016f, 1.483f, 0, 0,
                -0.03f, 0.05f, -0.02f, 1, 0};
        ColorMatrix colorMatrix = new ColorMatrix(array);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }

    // green效果矩阵
    public static Bitmap changeToGreen(Bitmap bitmap) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float[] array = {1,0,0,0,0,
                0,1.4f,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0};
        ColorMatrix colorMatrix = new ColorMatrix(array);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }

    // yellow效果矩阵
    public static Bitmap changeToYellow(Bitmap bitmap) {

        int width, height;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float[] array = {1,0,0,0,50,
                0,1,0,0,50,
                0,0,1,0,0,
                0,0,0,1,0  };
        ColorMatrix colorMatrix = new ColorMatrix(array);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return grayBitmap;
    }

}
