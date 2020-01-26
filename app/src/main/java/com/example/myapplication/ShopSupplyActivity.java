package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopSupplyActivity extends AppCompatActivity {
    private AWSconnectShop con;
    private TextView button1;
    private TextView button2;
    private TextView button3;
    private TextView button4;
    private TextView now_page;
    private TextView all_page;
    private int now_page_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_supply);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        button3 = findViewById(R.id.button8);
        button4 = findViewById(R.id.button9);
        now_page = findViewById(R.id.textView10);
        all_page = findViewById(R.id.textView12);
        now_page_num = 1;
        now_page.setText(String.valueOf(now_page_num));
        con = new AWSconnectShop(button1,button2,button3,button4, now_page, all_page);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/ShowGoodsMET.php");//接続するphpファイルの決定
        String Values = new String("a=0");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, ShopMachineActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        if (now_page_num > 1) {
            now_page_num--;
            button1.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            now_page.setText(String.valueOf(now_page_num));
            con = new AWSconnectShop(button1, button2, button3, button4, now_page, all_page);//idをawsconnectに送る
            String URL = new String("http://13.113.228.107/ShowGoodsMET.php");//接続するphpファイルの決定
            String Values = new String("a=2");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
            con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
        }
    }

    public void onClick4(View v) {
        int all_page_num = Integer.parseInt(all_page.getText().toString());
        if (all_page_num > now_page_num) {
            now_page_num++;
            button1.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            now_page.setText(String.valueOf(now_page_num));
            con = new AWSconnectShop(button1, button2, button3, button4, now_page, all_page);//idをawsconnectに送る
            String URL = new String("http://13.113.228.107/ShowGoodsMET.php");//接続するphpファイルの決定
            String Values = new String("a=2");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
            con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
        }
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

