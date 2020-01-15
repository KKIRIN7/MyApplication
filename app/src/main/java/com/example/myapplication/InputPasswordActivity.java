package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class InputPasswordActivity extends AppCompatActivity {

    private HmacMD5 hash = new HmacMD5();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        TextView textview = (TextView) this.findViewById(R.id.editText5);
        String s = textview.getText().toString();
        String password = hash.md5(s);

        textView = findViewById(R.id.editText5);
        textView.setText(password);

        Intent intent = getIntent();
        int judge = intent.getIntExtra("source",0);

        if (judge == 0){
            intent = new Intent(this, InputMailActivity.class);
            startActivity(intent);
        }
        if (judge == 1){
            intent = new Intent(this, InputNewPasswordActivity.class);
            startActivity(intent);
        }
    }
}
