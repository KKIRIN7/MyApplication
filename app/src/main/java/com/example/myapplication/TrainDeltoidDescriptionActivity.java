package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TrainDeltoidDescriptionActivity extends AppCompatActivity {
    private AWSconnect1 con;
    TextView text = findViewById(R.id.textview1);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_deltoid_description);
        Intent intent = getIntent();
        String judge = intent.getStringExtra("source1");
        con = new AWSconnect1(text);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/ShowTrainDetailMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a="+judge);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe, dfaifd);//第一引数にURL、第二引数以降にphpに送りたいものを入れる

    }
}
