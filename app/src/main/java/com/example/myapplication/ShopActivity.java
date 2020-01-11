package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, ShopSupplyActivity.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, ShopMachineActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick4(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick5(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
