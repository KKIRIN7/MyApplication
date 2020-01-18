package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IrregularPasswordActivity extends AppCompatActivity {

    private HmacMD5 hash = new HmacMD5();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irregular_password);
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        TextView textview = (TextView) this.findViewById(R.id.editText);
        //String s = textview.getText().toString();
        //String password = hash.md5(s);

        String password = textview.getText().toString();

        Intent intent = getIntent();
        int judge = intent.getIntExtra("source",0);
        String key1 = intent.getStringExtra("key1");
        String key2 = intent.getStringExtra("key2");

        if(password.equals(key2)) {
            if (judge == 0) {
                intent.putExtra("key1", key1);
                intent = new Intent(this, InputMailActivity.class);
                startActivity(intent);
            }
            if (judge == 1) {
                intent.putExtra("key1", key1);
                intent = new Intent(this, InputNewPasswordActivity.class);
                startActivity(intent);
            }
        }else{
            intent = new Intent(this, IrregularPasswordActivity.class);
            intent.putExtra("source", judge);
            intent.putExtra("key1", key1);
            intent.putExtra("key2", key2);
            startActivity(intent);
        }
    }
}