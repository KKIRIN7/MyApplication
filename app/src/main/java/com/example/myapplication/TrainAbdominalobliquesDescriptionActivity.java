package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrainAbdominalobliquesDescriptionActivity extends AppCompatActivity {
    private AWSconnect2 con;
    private TextView text;
    private TextView text2;
    private String TrainName;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_abdominalobliques_description);
        Intent intent = getIntent();

        String judge = intent.getStringExtra("source1");
        this.TrainName = judge;
        text = findViewById(R.id.textview1);
        text2 = findViewById(R.id.textview2);
        con = new AWSconnect2(text,text2);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/ShowTrainDetailMET.php");//接続するphpファイルの決定
        String dfaifd = new String("a="+judge);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe,dfaifd);//第一引数にURL、第二引数以降にphpに送りたいのを入れる
        text.setText(dfaifd);
    }
    public void onClick1(View v) {
        String tenpo = text2.getText().toString();
        if(tenpo.equals("0")){
            Intent intent = new Intent(this,MeasurementTimerActivity.class);
            intent.putExtra("InputTrainName", TrainName);
            intent.putExtra("ExistTrainName", true);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,MeasurementTempoActivity.class);
            intent.putExtra("TimerTrainName", TrainName);
            intent.putExtra("ExistTrainName", true);
            startActivity(intent);
        }
    }
}
