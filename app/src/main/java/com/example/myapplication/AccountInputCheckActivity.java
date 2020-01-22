package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccountInputCheckActivity extends AppCompatActivity {

    private AWSconnect1copy con;
    private EditText editTextKey, editTextValue;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private TextView a;
    private HmacMD5 hash = new HmacMD5();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_input_check);

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

        a = findViewById(R.id.textView2);
        con = new AWSconnect1copy(a);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/CreateAccountMOJ.php");//接続するphpファイルの決定
        String Values = new String("a=" + mail_address + "&b=" + user_name + "&c=" + hashpass);
//        String mail = new String("a=" + mail_address);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
//        String user = new String( "b=" + user_name);
//        String pass = new String( "c=" + password);
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }
    public void onClick0(View v) {
        String Input_result = a.getText().toString();
        if (Input_result.equals("登録完了")) {
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
