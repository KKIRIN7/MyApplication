package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainBigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_big);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, TrainCenterActivity.class);
        startActivity(intent);
    }
    public void onClick2(View v) {
        Intent intent = new Intent(this, TrainArmActivity.class);
        startActivity(intent);
    }
    public void onClick3(View v) {
        Intent intent = new Intent(this, TrainLegsActivity.class);
        startActivity(intent);
    }
    public void onClick4(View v) {
        Intent intent = new Intent(this, TrainBackActivity.class);
        startActivity(intent);
    }
}
