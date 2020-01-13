package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainArmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arm);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, TrainDeltoidActivity.class);
        startActivity(intent);
    }
    public void onClick2(View v) {
        Intent intent = new Intent(this, TrainBicepsActivity.class);
        startActivity(intent);
    }
    public void onClick3(View v) {
        Intent intent = new Intent(this, TrainTricepsActivity.class);
        startActivity(intent);
    }
}
