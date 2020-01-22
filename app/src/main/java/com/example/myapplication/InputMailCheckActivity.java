package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputMailCheckActivity extends AppCompatActivity {

    private AWSconnectSelect con;
    private EditText editTextKey, editTextValue;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mail_check);

        textview = (TextView) this.findViewById(R.id.textView2);

        if (helper == null) {
            helper = new MyOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getWritableDatabase();
        }

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        String key1 = intent.getStringExtra("key1");
        String key2 = intent.getStringExtra("key2");

        con = new AWSconnectSelect(textview,0);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountUpdateMET.php");//接続するphpファイルの決定
        String Values = new String("a=" + key1 + "&b=" + key2 + "&c=" + key + "&d=");
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }

    public void onClick0(View v) {
        String result = textview.getText().toString();
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        String key1 = intent.getStringExtra("key1");

        if (!(result.equals("失敗しました"))) {
            ContentValues values = new ContentValues();
            values.put("mailaddress", key);
            values.put("login", 0);
            db.update("informationuserdb", values, "mailaddress = \"" + key1 + "\"", null);
            intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        } else {
            intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
        }
    }
}
