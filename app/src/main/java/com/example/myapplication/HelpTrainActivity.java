package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpTrainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_train);
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, HelpMeasurmentActivity.class);
        startActivity(intent);
    }
}
