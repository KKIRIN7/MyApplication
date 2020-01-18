package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }

        String key = textview.getText().toString();

        Intent intent = getIntent();
        String key1 = intent.getStringExtra("key1");
        String key2 = intent.getStringExtra("key2");


        ContentValues values = new ContentValues();
        values.put("mailaddress", key);
        values.put("login", 0);
        db.update("informationuserdb",values, "mailaddress = \"" + key1 + "\"",null);

        con = new AWSconnect1(textview);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountUpdateMET.php");//接続するphpファイルの決定
        String Values = new String("a=" + key1 + "&b=" + key2 + "&c=" + key + "&d=\"\"");
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる

        //textview.setText(key1);

        intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

}
