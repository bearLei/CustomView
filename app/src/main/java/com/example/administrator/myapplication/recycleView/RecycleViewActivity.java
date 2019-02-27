package com.example.administrator.myapplication.recycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class RecycleViewActivity extends AppCompatActivity {
    private CustomRecycleView customRecycleView;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        customRecycleView = findViewById(R.id.recycleView);

        initAdapter();
        customRecycleView.setAdapter(adapter);
    }

    private void initAdapter(){
        adapter = new Adapter() {
            @Override
            public View onCreateViewHolder(ViewGroup view, ViewGroup parent, int position) {
                View inflate = LayoutInflater.from(RecycleViewActivity.this).inflate(R.layout.item_adapter, parent);
//                TextView itemId = inflate.findViewById(R.id.item_tv);
                return inflate;
            }

            @Override
            public View onBindViewHolder(ViewGroup view, ViewGroup parent, int position) {
                TextView itemId = view.findViewById(R.id.item_tv);
                itemId.setText("当前是第几个item--->"+position);
                return view;
            }

            @Override
            public int getItemViewType() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public int getItemCount() {
                return 30;
            }

            @Override
            public int getHeigth() {
                return 200;
            }
        };
    }

}
