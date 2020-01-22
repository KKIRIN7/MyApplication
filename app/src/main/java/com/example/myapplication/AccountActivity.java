package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AccountActivity extends AppCompatActivity {

    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private AWSconnect4 con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "informationuserdb",
                new String[] { "mailaddress", "login" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String mymail = cursor.getString(0);

        TextView b = findViewById(R.id.textView6);//渡したいtextviewのidをaに入れる
        TextView a = findViewById(R.id.textView7);//渡したいtextviewのidをaに入れる
        TextView c = findViewById(R.id.textView8);//渡したいtextviewのidをaに入れる
        TextView d = findViewById(R.id.textView9);//渡したいtextviewのidをaに入れる
        con = new AWSconnect4(a, b, c, d);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a=" + mymail);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe, dfaifd);//第一引数にURL、第二引数以降にphpに送りたいものを入れる


    }
    public void onClick0(View v) {
        TextView a = findViewById(R.id.textView7);//渡したいtextviewのidをaに入れる
        TextView c = findViewById(R.id.textView8);//渡したいtextviewのidをaに入れる
        String key1 = a.getText().toString();
        String key2 = c.getText().toString();

        Intent intent = new Intent(this, InputPasswordActivity.class);
        int source = 0;
        intent.putExtra("source", source);
        intent.putExtra("key1", key1);
        intent.putExtra("key2", key2);

        startActivity(intent);
    }

    public void onClick1(View v) {
        TextView a = findViewById(R.id.textView7);//渡したいtextviewのidをaに入れる
        TextView c = findViewById(R.id.textView8);//渡したいtextviewのidをaに入れる
        String key1 = a.getText().toString();
        String key2 = c.getText().toString();

        Intent intent = new Intent(this, InputPasswordActivity.class);
        int source = 1;
        intent.putExtra("source", source);
        intent.putExtra("key1", key1);
        intent.putExtra("key2", key2);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}