package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {
    private AWSconnect4Shop con;
    private TextView button1;
    private TextView button2;
    private TextView button3;
    private TextView button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        button3 = findViewById(R.id.button8);
        button4 = findViewById(R.id.button9);
        con = new AWSconnect4Shop(button1,button2,button3,button4);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/ShowGoodsMET.php");//接続するphpファイルの決定
        String Values = new String("a=2");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, ShopSupplyActivity.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, ShopMachineActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick4(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick5(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick6(View v) {
        Intent intent = new Intent(this,ShopdummyActivity.class);
        String source1 = button1.getText().toString();
        if (!source1.equals("")) {
            intent.putExtra("source", source1);
            startActivity(intent);
        }
    }
    public void onClick7(View v) {
        Intent intent = new Intent(this,ShopdummyActivity.class);
        String source2 = button2.getText().toString();
        if (!source2.equals("")) {
            intent.putExtra("source", source2);
            startActivity(intent);
        }
    }
    public void onClick8(View v) {
        Intent intent = new Intent(this,ShopdummyActivity.class);
        String source3 = button3.getText().toString();
        if (!source3.equals("")) {
            intent.putExtra("source", source3);
            startActivity(intent);
        }
    }
    public void onClick9(View v) {
        Intent intent = new Intent(this,ShopdummyActivity.class);
        String source4 = button4.getText().toString();
        if (!source4.equals("")) {
            intent.putExtra("source", source4);
            startActivity(intent);
        }
    }
}
