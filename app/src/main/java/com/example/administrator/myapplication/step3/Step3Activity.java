package com.example.administrator.myapplication.step3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class Step3Activity extends AppCompatActivity {

    private FlowLayout flowLayout;
    private Button add,remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        flowLayout = findViewById(R.id.flowLayout);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View view = LayoutInflater.from(Step3Activity.this).inflate(R.layout.flowlayout_item,flowLayout,false);
               TextView textView =  view.findViewById(R.id.item);
               textView.setText("测试数据"+(int)(Math.random()*10));
               view.setBackground(getResources().getDrawable(R.drawable.flow_layout_default_bg));
                flowLayout.addContainerView(view);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeView();
            }
        });

    }
}
