package com.example.administrator.myapplication.setp2;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * 自定义1个View
 * 并且实现动画功能
 */
public class CustomBall02 extends View {


    private Paint mPaint;
    private int mRadius;

    private int mWidth;//控件宽
    private int mHeight;//控件高

    private float mCenterX;//圆心x坐标
    private float mCenterY;//圆心Y坐标

    private float scale;//缩放比例

    private ValueAnimator animator;
    public CustomBall02(Context context) {
        super(context);
        init();
    }

    public CustomBall02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBall02(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mRadius = 100;
        scale = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCenterX,mCenterY,mRadius*scale,mPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widMode == MeasureSpec.EXACTLY){
            mWidth = widSize;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }

        if (widMode == MeasureSpec.AT_MOST){
            mWidth = Math.min(widSize,mRadius *2);
        }
        if (heightMode == MeasureSpec.AT_MOST){
            mHeight = Math.min(heightSize,mRadius*2);
        }
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    public void starAnimation() {
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0,500);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
//                    scale = (float) animation.getAnimatedValue();
                    mCenterX = (float) animation.getAnimatedValue();
                    Log.d("lei","CenterX-->"+mCenterX);
                    invalidate();
                }
            });
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(-1);
            animator.setInterpolator(new DecelerateInterpolator());
        }
        animator.start();
    }

    public void stopAnimation(){
        if (animator != null && animator.isRunning()){
            animator.cancel();
        }
    }
}
