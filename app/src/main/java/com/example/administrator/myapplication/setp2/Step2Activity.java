package com.example.administrator.myapplication.setp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.myapplication.R;

public class Step2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        CustomBall02 customBall02 = findViewById(R.id.customball_02);
        customBall02.starAnimation();
    }
}
