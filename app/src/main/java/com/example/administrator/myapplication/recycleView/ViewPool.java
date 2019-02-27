package com.example.administrator.myapplication.recycleView;

import android.view.View;

import java.util.Stack;

/**
 * 回收池
 */
public class ViewPool {

    //栈数组
    private Stack<View>[] viewPool;
    public ViewPool(int viewTypeLength) {
        viewPool = new Stack[viewTypeLength];//有几种ViewType就初始化几个栈
        for (int i = 0; i < viewTypeLength; i++) {
            viewPool[i] = new Stack<>();
        }
    }
    /**
     * 栈内push元素
     * @param view 要被丢入的view
     * @param type ViewType类型
     */
    public void put(View view,int type){
        viewPool[type].push(view);
    }
    /**
     * 栈内取元素
     * @param type ViewType类型
     * @return 栈内的View
     */
    public View get(int type){
        if (viewPool[type] != null || viewPool.length > 0) {
            View view = viewPool[type].pop();
            return view;
        }
        return null;
    }
}
