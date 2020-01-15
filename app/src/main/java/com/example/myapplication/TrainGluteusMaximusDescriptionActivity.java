package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TrainGluteusMaximusDescriptionActivity extends AppCompatActivity {
    private AWSconnect1copy con;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_gluteus_maximus_description);
        Intent intent = getIntent();

        String judge = intent.getStringExtra("source1");
        text = findViewById(R.id.textview1);

        con = new AWSconnect1copy(text);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/ShowTrainDetailMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a="+judge);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe,dfaifd);//第一引数にURL、第二引数以降にphpに送りたいのを入れる
        text.setText(dfaifd);
    }
    }
