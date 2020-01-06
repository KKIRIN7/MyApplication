package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, InputPasswordActivity.class);
        int source = 0;
        intent.putExtra("source", source);
        startActivity(intent);
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, InputPasswordActivity.class);
        int source = 1;
        intent.putExtra("source", source);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}