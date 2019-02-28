package com.example.administrator.myapplication.step3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class FlowLayout extends ViewGroup {

    private static final int mDefaultWidth = 500;//默认宽
    private static final int mDefaultHeight = 300;//默认高

    private List<View> views;//全部子view列表
    private HashMap<Integer,LineInfo> mLineMap;//保存每行的信息列表

    private int mWidth;//测量的宽
    private int mHeight;//测量的高
    private int lineNum;//行数

    public FlowLayout(Context context) {
        super(context);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        views = new ArrayList<>();
        lineNum = 1;
        mLineMap = new HashMap<>();
        mLineMap.put(lineNum,new LineInfo());
    }

    public void  addContainerView(View view){
        if (views != null){
            views.add(view);
        }
        lineNum = 1;
        this.addView(view);
        requestLayout();
    }

    public void removeView(){
//        if (views != null && views.contains(view)){
//            views.remove(view);
//        }
        if (views != null && views.size() > 0){
            views.remove(views.size()-1);
        }
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widSize = MeasureSpec.getSize(widthMeasureSpec);//父view的测量宽度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;//父view的可用宽度 除去padding
        int heitht = 0;//测量高
        int lineWidth = 0;//每行的宽度
        int lineHeight = 0;//行高
        //通过子View宽度总和计算出宽度
        int chileViewSize = views.size();
        //先new
        for (int i = 0; i < chileViewSize; i++) {
            View child = views.get(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;//获取子view的宽度
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;//获取子view的高度
            //计算出父view的可用宽度
//            width =  widSize - getPaddingLeft() - getPaddingRight();
            //对比啥时候换行:每行宽度超过父view可用宽度就换行
            if (lineWidth + childWidth > widSize - getPaddingLeft()-getPaddingRight()){
                lineNum++;
                mLineMap.put(lineNum,new LineInfo());
                //换行
                mWidth = Math.max(width,lineWidth);//取2个最大值
                //重置行宽
                lineWidth = childWidth;
                //父view的高度 = 各行高度总和
                heitht += lineHeight;
                //换行：当前行高 = 当前行的第一个View高
                lineHeight = childHeight;
            }else {
                //叠加宽
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            // 最后一个控件
            if (i == chileViewSize - 1)
            {
                mWidth = Math.max(lineWidth, width);
                heitht += lineHeight;
            }
            LineInfo info = mLineMap.get(lineNum);
            ArrayList<View> views = info.getmContaineView();
            if (views == null){
                views = new ArrayList<>();
            }
            if (!views.contains(child)) {
                views.add(child);
            }
            info.setmContaineView(views);
            info.setmLineHeigth(lineHeight);
            info.setmLinewidth(lineWidth);
        }
        mHeight = heitht;
        //根据模式来判断是否采用自己计算的值
        setMeasuredDimension(
                widMode == MeasureSpec.AT_MOST? mWidth:widSize,
                heightMode == MeasureSpec.AT_MOST ?mHeight :  heightSize);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //布局
//        if (!changed) return;

        t = getPaddingTop();

        Set<Map.Entry<Integer, LineInfo>> entries = mLineMap.entrySet();
        Iterator<Map.Entry<Integer, LineInfo>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, LineInfo> next = iterator.next();
            //先处理第一行的布局摆放
            LineInfo value = next.getValue();
            ArrayList<View> views = value.getmContaineView();//每行的子view
            if (views == null || views.size() == 0) return;
            l = getPaddingLeft();
            //水平摆放 一行
            for (int i = 0; i < views.size(); i++) {
                View child = views.get(i);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                child.layout(l, t,
                            l+child.getMeasuredWidth(),
                            t+child.getMeasuredHeight());

                l += child.getMeasuredWidth();
            }
            t += value.getmLineHeigth();
        }

    }


    @Override
    public MarginLayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
