package com.example.administrator.myapplication.step;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义View step one
 * 自定义1个 随手指移动的小球
 */

public class CustomBall extends View {

    private Paint mPaint;

    private int mRadius;//半径

    private int mColor;//球的颜色

    private float mCurrentX;//当前的x坐标

    private float mCurrentY;//当前的y坐标

    public CustomBall(Context context) {
        super(context);
    }

    public CustomBall(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomBall(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mRadius = 100;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       canvas.drawCircle(mCurrentX,mCurrentY,mRadius,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mCurrentX = event.getX();
        mCurrentY = event.getY();
        invalidate();
        return true;
    }
}
