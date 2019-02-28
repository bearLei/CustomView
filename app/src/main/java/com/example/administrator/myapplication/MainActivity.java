package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.myapplication.recycleView.RecycleViewActivity;
import com.example.administrator.myapplication.setp2.Step2Activity;
import com.example.administrator.myapplication.step.Step1Activity;
import com.example.administrator.myapplication.step3.Step3Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.jump_recycleViewActy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecycleViewActivity.class));
            }
        });
        findViewById(R.id.jump_stepOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Step1Activity.class));
            }
        });
        findViewById(R.id.jump_steptwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Step2Activity.class));
            }
        });
        findViewById(R.id.jump_stepthree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Step3Activity.class));
            }
        });
    }


}
