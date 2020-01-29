package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InputNewPasswordActivity extends AppCompatActivity {

    private AWSconnect1 con;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private HmacMD5 hash = new HmacMD5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_new_password);
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        TextView textview = (TextView) this.findViewById(R.id.editText16);

        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }

        String key = textview.getText().toString();
        String password = hash.md5(key);

        Intent intent = getIntent();
        String key1 = intent.getStringExtra("key1");
        String key2 = intent.getStringExtra("key2");


        if(key.length() != 0) {
            ContentValues values = new ContentValues();
            con = new AWSconnect1(textview);//idをawsconnectに送る
            String URL = new String("http://13.113.228.107/AccountUpdateMET.php");//接続するphpファイルの決定
            String Values = new String("a=" + key1 + "&b=" + key2 + "&c=" + "&d=" + password);
            con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる

            //textview.setText(key1);

            intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        }
    }
}
