package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class TempoIntervalActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    int Check = 0;
    int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo_interval);

        Intent intent = getIntent();
        String trainname = intent.getStringExtra("TIATrainName");
        TextView textview = (TextView) this.findViewById(R.id.trainname2);

        final int Int_TempoSetNumber = intent.getIntExtra("Int_TempoSetNumber", 0);
        final int Int_NumberTime = intent.getIntExtra("Int_NumberTime", 0);
        final int Int_IntervalMiniute = intent.getIntExtra("Int_IntervalMiniute", 0);//何の名前で受け渡してきたか
        final int Int_IntervalSecond = intent.getIntExtra("Int_IntervalSecond", 0);//何の名前で受け渡してきたか
        time = (Int_IntervalMiniute * 60 + Int_IntervalSecond) * 1000;
        ((TextView) findViewById(R.id.Intervalview)).setText("残り" + Int_TempoSetNumber + "セット");


        textview.setText(trainname);
        final String String_TempoSetNumber = String.valueOf(Int_TempoSetNumber);
        final String String_NumberTime = String.valueOf(Int_NumberTime);
        final String String_IntervalMiniute = String.valueOf(Int_IntervalMiniute);
        final String String_IntervalSecond = String.valueOf(Int_IntervalSecond);

     //   Check = 1;
        CountDownTimer = new CountDownTimer(time, 100) {
            Intent intent = getIntent();

            String String_trainname = intent.getStringExtra("TIATrainName");

            String String_InputTrainName = intent.getStringExtra("InputTrainName");
            String String_TempoSetNumber = String.valueOf(Int_TempoSetNumber);
            String String_NumberTime = String.valueOf(Int_NumberTime);
            String String_IntervalMiniute = String.valueOf(Int_IntervalMiniute);
            String String_IntervalSecond = String.valueOf(Int_IntervalSecond);

            @Override
            public void onTick(long millisUntilFinished) {
                int time = (int) millisUntilFinished / 1000;
                int Mintime = (int) millisUntilFinished / 60000;
                int SecTime = (int) millisUntilFinished % 60000;
                int SEctime = (int) SecTime / 1000;
                ((TextView) findViewById(R.id.Intervaltime)).setText("あと" + Mintime + "分" + SEctime + "秒");
            }

            @Override
            public void onFinish() {
                    Intent intent = new Intent(TempoIntervalActivity.this, TimerSetCountActivity.class);
                    intent.putExtra("TSCTrainName", String_trainname);
                    intent.putExtra("Int_TempoSetNumber", String_TempoSetNumber);
                    intent.putExtra("Int_IntervalMiniute", String_IntervalMiniute);
                    intent.putExtra("Int_IntervalSecond", String_IntervalSecond);
                    intent.putExtra("Int_NumberTime", String_NumberTime);
                    startActivity(intent);
            }
        }.start();
    }

/*
    public void onClick0(View v) throws InterruptedException {

        Intent intent = getIntent();
        TextView trainname = (TextView) findViewById(R.id.trainname2);
        String String_trainname = trainname.getText().toString();

        int Int_TempoSetNumber = intent.getIntExtra("Int_TempoSetNumber", 0);
        int Int_NumberTime = intent.getIntExtra("Int_NumberTime", 0);
        int Int_IntervalMiniute = intent.getIntExtra("Int_IntervalMiniute", 0);//何の名前で受け渡してきたか
        int Int_IntervalSecond = intent.getIntExtra("Int_IntervalSecond", 0);//何の名前で受け渡してきたか
        String String_TempoSetNumber = String.valueOf(Int_TempoSetNumber);
        String String_NumberTime = String.valueOf(Int_NumberTime);
        String String_IntervalMiniute = String.valueOf(Int_IntervalMiniute);
        String String_IntervalSecond = String.valueOf(Int_IntervalSecond);
        //if(Check == 1){
           // CountDownTimer.cancel();
        //}
        Intent intent1 = new Intent(this, MeasurementTempoActivity.class);
        intent.putExtra("TimerTrainName", String_trainname);
        intent1.putExtra("Int_TempoSetNumber", String_TempoSetNumber);
        intent1.putExtra("Int_IntervalMiniute", String_IntervalMiniute);
        intent1.putExtra("Int_IntervalSecond", String_IntervalSecond);
        intent1.putExtra("Int_NumberTime", String_NumberTime);

        startActivity(intent1);

    }*/
}

//おそらくonCreateの中でカウントダウンを動かしているためストップは不可、ボタンごと消去を要求