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
        EditText editText1 = (EditText) findViewById(R.id.editText5);//editText5を指定
        String user_name = editText1.getText().toString();//editText5の値を取り出す
        EditText editText2 = (EditText) findViewById(R.id.editText6);//editText6を指定
        String mail_address = editText2.getText().toString();//editText6の値を取り出す
        EditText editText3 = (EditText) findViewById(R.id.editText7);//editText7を指定
        String password = editText3.getText().toString();//editText7の値を取り出す
        if (!user_name.equals("") & !mail_address.equals("") & !password.equals("")) {
            Intent intent = new Intent(this, AccountInputCheckActivity.class);
            intent.putExtra("USER_NAME", user_name);
            intent.putExtra("MAIL_ADDRESS", mail_address);
            intent.putExtra("PASSWORD", password);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AccountInputErrorActivity.class);
            startActivity(intent);
        }
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
