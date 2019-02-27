package com.example.administrator.myapplication.recycleView;

import android.view.View;
import android.view.ViewGroup;

/**
 * 数据适配器
 */
public interface Adapter {
    View onCreateViewHolder(ViewGroup view, ViewGroup parent, int position);
    View onBindViewHolder(ViewGroup view, ViewGroup parent, int position);
    int getItemViewType();
    long getItemId(int position);
    int getItemCount();

    int getHeigth();

}
