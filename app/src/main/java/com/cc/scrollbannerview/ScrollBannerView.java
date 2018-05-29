package com.cc.scrollbannerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 创建日期：2018/5/28 on 20:57
 * 描述：
 * 作者：郭士超
 * QQ：1169380200
 */

public class ScrollBannerView  extends View {

    private Bitmap mBannerBitmap;

    private Rect rectSrc;
    private Rect rectDst;

    private int screenWidth;
    private int screenHeight;

    public ScrollBannerView(Context context) {
        super(context);
    }

    public ScrollBannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollBannerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBannerImageResource(@DrawableRes int resId) {
        mBannerBitmap = BitmapFactory.decodeResource(getResources(), resId);

        // 获取屏幕宽高
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        // 把图片修改为屏幕大小
        mBannerBitmap = scaleBitmap(mBannerBitmap, screenWidth, screenHeight);
    }

    public void notifyLayoutChange() {
        // 获取view当前位置
        int[] location = new int[2];
        getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        // 定义需要截取图片的位置
        int showLeft = 0;
        int showTop = y;
        int showRight = mBannerBitmap.getWidth();
        int showBottom = y + getHeight();

        // 显示在屏幕上view的宽高
        int width = getWidth();
        int height = y + getHeight() > screenHeight ? screenHeight - y : getHeight();

        // 图片范围
        rectSrc = new Rect(showLeft, showTop, showRight, showBottom);
        // 绘制范围
        rectDst = new Rect(0, showTop < 0 ? -showTop : 0, width, height);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制
        if (mBannerBitmap != null && rectSrc != null && rectDst != null) {
            canvas.drawBitmap(mBannerBitmap, rectSrc, rectDst, null);
        }
    }

    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight); // 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

}
