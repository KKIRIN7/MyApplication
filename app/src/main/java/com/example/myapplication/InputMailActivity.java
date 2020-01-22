package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputMailActivity extends AppCompatActivity {

    private AWSconnect1 con;
    private TextView textView;
    private EditText editTextKey, editTextValue;
    private MyOpenHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mail);
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        TextView textview = (TextView) this.findViewById(R.id.editText3);
        String key = textview.getText().toString();

        Intent intent = getIntent();
        String key1 = intent.getStringExtra("key1");
        String key2 = intent.getStringExtra("key2");
        intent = new Intent(this, InputMailCheckActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("key1", key1);
        intent.putExtra("key2", key2);
        startActivity(intent);
    }

}
