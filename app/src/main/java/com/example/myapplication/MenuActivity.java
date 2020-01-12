package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, HelpTrainActivity.class);
        startActivity(intent);
    }
    public void onClick2(View v) {
        Intent intent = new Intent(this, TrainBigActivity.class);
        startActivity(intent);
    }
    public void onClick3(View v) {
        Intent intent = new Intent(this, InputTrainNameActivity.class);
        startActivity(intent);
    }
        public void onClick4(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }
    public void onClick5(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
}
