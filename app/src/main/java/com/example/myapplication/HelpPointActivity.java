package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpPointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_point);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
