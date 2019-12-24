package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpMeasurmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_measurment);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, HelpCalendarActivity.class);
        startActivity(intent);
    }
}
