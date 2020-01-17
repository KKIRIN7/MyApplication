package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountInputCheckActivity extends AppCompatActivity {

    private AWSconnect1copy con;
    private TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_input_check);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("USER_NAME");
        String mail_address = intent.getStringExtra("MAIL_ADDRESS");
        String password = intent.getStringExtra("PASSWORD");

        a = findViewById(R.id.textView2);
        con = new AWSconnect1copy(a);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/CreateAccountMOJ.php");//接続するphpファイルの決定
        String Values = new String("a=" + mail_address + "&b=" + user_name + "&c=" + password);
//        String mail = new String("a=" + mail_address);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
//        String user = new String( "b=" + user_name);
//        String pass = new String( "c=" + password);
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }
    public void onClick0(View v) {
        String Input_result = a.getText().toString();
        if (Input_result.equals("登録完了")) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
