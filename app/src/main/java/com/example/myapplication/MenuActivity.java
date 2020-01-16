package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MenuActivity extends AppCompatActivity {

    private AWSconnectSelect con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView a = findViewById(R.id.textView);//渡したいtextviewのidをaに入れる

        con = new AWSconnectSelect(a,3);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a=12345@gmail.com");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe, dfaifd);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this, HelpTrainActivity.class);
        startActivity(intent);
    }
    public void onClick2(View v) {
        Intent intent = new Intent(this, TrainBigActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        Intent intent = new Intent(this, InputTrainNameActivity.class);
        startActivity(intent);
    }

    public void onClick4(View v) {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }

    public void onClick5(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
}
