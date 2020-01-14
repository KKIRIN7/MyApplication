package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TrainHamstringActivity extends AppCompatActivity {
    private AWSconnect4 con;
    private TextView button1;
    private TextView button2;
    private TextView button3;
    private TextView button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_hamstring);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        con = new AWSconnect4(button1,button2,button3,button4);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/ShowTrainMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a=ハムストリング");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe, dfaifd);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }
    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    public void onClick1(View v) {
        Intent intent = new Intent(this,TrainCalfDescriptionActivity.class);
        String source1 = button1.getText().toString();
        intent.putExtra("source1", source1);
        startActivity(intent);
    }
    public void onClick2(View v) {
        Intent intent = new Intent(this,TrainCalfDescriptionActivity.class);
        String source2 = button2.getText().toString();
        intent.putExtra("source1", source2);
        startActivity(intent);
    }
    public void onClick3(View v) {
        Intent intent = new Intent(this,TrainCalfDescriptionActivity.class);
        String source3 = button3.getText().toString();
        intent.putExtra("source1", source3);
        startActivity(intent);
    }
}
