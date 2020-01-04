package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountInputErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_input_error);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, startActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, AccountInputActivity.class);
        startActivity(intent);
    }
}
