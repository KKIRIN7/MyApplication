package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccountInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_input);

    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, startActivity.class);
        startActivity(intent);
    }
}
