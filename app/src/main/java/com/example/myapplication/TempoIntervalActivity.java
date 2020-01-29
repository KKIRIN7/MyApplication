package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TempoIntervalActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    int Check = 0;
    int time = 0;
    int spare[] = new int[4];
    int Int_DBsetsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo_interval);

        Intent intent = getIntent();
        String trainname = intent.getStringExtra("TIATrainName");
        TextView textview = (TextView) this.findViewById(R.id.trainname2);

        final int Int_TempoSetNumber = intent.getIntExtra("Int_TempoSetNumber", 0);
        final int Int_NumberTime = intent.getIntExtra("Int_NumberTime", 0);
        final int Int_IntervalMiniute = intent.getIntExtra("Int_IntervalMiniute", 0);
        final int Int_IntervalSecond = intent.getIntExtra("Int_IntervalSecond", 0);
        Int_DBsetsend = intent.getIntExtra("DBSetSend",0);//////////////////////
        time = (Int_IntervalMiniute * 60 + Int_IntervalSecond) * 1000;
        ((TextView) findViewById(R.id.Intervalview)).setText("残り" + Int_TempoSetNumber + "セット");
        textview.setText(trainname);

        CountDownTimer = new CountDownTimer(time, 100) {
            Intent intent = getIntent();

            String String_trainname = intent.getStringExtra("TIATrainName");
            String String_InputTrainName = intent.getStringExtra("InputTrainName");
            String String_TempoSetNumber = String.valueOf(Int_TempoSetNumber);
            String String_NumberTime = String.valueOf(Int_NumberTime);
            String String_IntervalMiniute = String.valueOf(Int_IntervalMiniute);
            String String_IntervalSecond = String.valueOf(Int_IntervalSecond);

            @Override
            public void onTick(long millisUntilFinished) {//カウントダウンを行う処理
                int sparetime = (int)millisUntilFinished;
                int sparetime2 = (int)millisUntilFinished;
                int a = 0;
                for(int i = 0; i < 4; i++) {
                    spare[a] = sparetime % 10;
                    sparetime = sparetime / 10;
                    a++;
                }

                int Mintime = sparetime2 / 60000;
                int Sectime = sparetime2 % 60000;
                int SEctime = Sectime / 1000;

                if( 5 <= spare[2] && spare[2] <= 9){
                    SEctime++;
                }
                if(SEctime == 60){
                    SEctime = 0;
                }
                ((TextView) findViewById(R.id.Intervaltime)).setText("あと" + Mintime + "分" + SEctime + "秒");//タイマー状況を画面に反映
            }

            @Override
            public void onFinish() {//インターバルが終了次第、自動で1つ前の画面に戻る
                String String_TempoTempo = "1";

                Intent intent = new Intent(TempoIntervalActivity.this, TimerSetCountActivity.class);
                intent.putExtra("TSCTrainName", String_trainname);
                intent.putExtra("Int_TempoSetNumber", String_TempoSetNumber);
                intent.putExtra("Int_IntervalMiniute", String_IntervalMiniute);
                intent.putExtra("Int_IntervalSecond", String_IntervalSecond);
                intent.putExtra("Int_NumberTime", String_NumberTime);
                intent.putExtra("TempoTempo", String_TempoTempo);
                intent.putExtra("DBSetSend", Int_DBsetsend);
                startActivity(intent);
            }
        }.start();
    }
}