package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HikitugiCheckActivity extends AppCompatActivity {

    private AWSconnectHikitugi con;
    private TextView textView;
    private EditText editTextKey, editTextValue;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private HmacMD5 hash = new HmacMD5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikitugi_check);

        if (helper == null) {
            helper = new MyOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getWritableDatabase();
        }

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("USER_NAME");
        String mail_address = intent.getStringExtra("MAIL_ADDRESS");
        String password = intent.getStringExtra("PASSWORD");

        String hashpass = hash.md5(password);

        textView = findViewById(R.id.textView2);
        con = new AWSconnectHikitugi(textView, user_name, mail_address, hashpass);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String Values = new String("a=" + mail_address);
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }
    public void onClick0(View v) {
        String hikitugi_result = textView.getText().toString();
        if (hikitugi_result.equals("引き継ぎ完了")) {
            Intent intent = getIntent();
            String mail_address = intent.getStringExtra("MAIL_ADDRESS");
            ContentValues values = new ContentValues();
            values.put("mailaddress", mail_address);
            values.put("login", 0);
            db.insert("informationuserdb", null, values);

            intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
