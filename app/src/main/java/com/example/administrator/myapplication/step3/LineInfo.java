package com.example.administrator.myapplication.step3;


import android.view.View;

import java.util.ArrayList;

/**
 * FlowLayout中保存每行信息的类
 */
public class LineInfo {

    private int mLineHeigth;
    private int mLinewidth;
    private ArrayList<View> mContaineView;


    public LineInfo() {
    }


    public int getmLineHeigth() {
        return mLineHeigth;
    }

    public void setmLineHeigth(int mLineHeigth) {
        this.mLineHeigth = mLineHeigth;
    }

    public int getmLinewidth() {
        return mLinewidth;
    }

    public void setmLinewidth(int mLinewidth) {
        this.mLinewidth = mLinewidth;
    }

    public ArrayList<View> getmContaineView() {
        return mContaineView;
    }

    public void setmContaineView(ArrayList<View> mContaineView) {
        this.mContaineView = mContaineView;
    }
}
