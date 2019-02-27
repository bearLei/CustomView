package com.example.administrator.myapplication.recycleView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义View
 */
public class CustomRecycleView extends ViewGroup {

    private ViewPool viewPool;//回收池
    private Adapter adapter;//数据适配器

    private List<View> views;//已经加载到屏幕上的View
    private long currentY;//记录在Y轴的滑动距离
    private long rowCount;//记录加载的总数据个数
    private int firstRow;//记录屏幕中的第一个View在数据内容中的位置
    private int scrollY;//RecycleView中第一个View的左上顶点距离屏幕的距离，用来判断是否移出屏幕
    private int width,height;//RecycleView的宽和高
    private int[] heights;//item的高度
    private int touchSlop;//滑动的最小距离
    private boolean needReLayout;

    public CustomRecycleView(Context context) {
        super(context);
        init(context);
    }

    public CustomRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        this.views = new ArrayList<>();
        this.needReLayout = true;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        touchSlop = viewConfiguration.getScaledTouchSlop();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int h = 0;
        //获取adapter数据的个数，并且获取每个item的高度赋给heights
        if (adapter != null){
            int itemCount = adapter.getItemCount();
            heights = new int[itemCount];
            for (int i = 0; i <itemCount; i++) {
                heights[i] = adapter.getHeigth();
            }
        }

        int tempH = sumArray(heights,0,heights.length);
        h = Math.min(heightSize,tempH);
        setMeasuredDimension(widthSize,h);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int sumArray(int[] heights, int firtst, int length) {
        int sum = 0 ;
        for (int i = firtst; i < length; i++) {
            sum += heights[i];
        }
        return sum;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needReLayout || changed) {
            views.clear();
            removeAllViews();
            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left = 0;
                int top = 0;
                int right = 0;
                int bottom = 0;
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = heights[i] + top;
                    View view = makeAndSteps(i, 0, top, width, bottom);
                    views.add(view);
                    top = bottom;
                }

            }
        }
    }

    /**
     *
     * @param i
     * @param left 左边距
     * @param top 上边距
     * @param right 右边距
     * @param bottom 下边距
     * @return
     */
    private View makeAndSteps(int i,int left, int top, int right, int bottom){
        //生成View
        View view = obtainView(i,right-left,bottom-top);

        //摆放View
        view.layout(left,top,right,bottom);
        return view;
    }


    private View obtainView(int i, int width, int height) {
        //先去回收池中查找
        int viewType = adapter.getItemViewType();
        View recycleView = viewPool.get(viewType);
        View view;
        if (recycleView == null){
            view = adapter.onCreateViewHolder((ViewGroup) recycleView,this,i);
            if (view== null){
                throw new RuntimeException("布局未填充");
            }
        }else {
            view = adapter.onBindViewHolder((ViewGroup) recycleView,this,i);
        }
        view.setTag(R.id.tag_view_type,viewType);
        view.measure(MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
        addView(view,0);
        return view;
    }


    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null){
            viewPool = new ViewPool(adapter.getItemViewType());
        }
        rowCount = adapter.getItemCount();
        scrollY = 0;
        firstRow = 0;
        needReLayout = true;
        requestLayout();
    }
}
