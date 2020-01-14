package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class TimerSetCountActivity extends AppCompatActivity {
    private TextView timerText;
    long Miniute = 0;
    CountDownTimer CountDownTimer;
    int Check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_set_count);

        Check = 0;
        Intent intent = getIntent();
        String TempoSetNumber = intent.getStringExtra("Int_TempoSetNumber");//何の名前で受け渡してきたか
        TextView textview = (TextView) this.findViewById(R.id.SET); //このアクティビティ内で受け取るBOX
        String IntervalMiniute = intent.getStringExtra("Int_IntervalMiniute");//何の名前で受け渡してきたか
        TextView textview2 = (TextView) this.findViewById(R.id.IntervalMiniute); //このアクティビティ内で受け取るBOX
        String IntervalSecond = intent.getStringExtra("Int_IntervalSecond");//何の名前で受け渡してきたか
        TextView textview3 = (TextView) this.findViewById(R.id.interval2); //このアクティビティ内で受け取るBOX
        String NumberTime = intent.getStringExtra("Int_NumberTime");//何の名前で受け渡してきたか
        TextView textview4 = (TextView) this.findViewById(R.id.timer); //このアクティビティ内で受け取るBOX

        textview.setText(String.valueOf(TempoSetNumber));
        textview2.setText(String.valueOf(IntervalMiniute));
        textview3.setText(String.valueOf(IntervalSecond));
        textview4.setText(String.valueOf(NumberTime));
        // 3分= 3x60x1000 = 180000 msec //回数
        int Int_NumberTime = Integer.parseInt(textview4.getText().toString());
        Miniute = (Int_NumberTime + 1) * 1000;

    }

    public void onClick0(View v) {
        TextView TempoSetNumber = (TextView) findViewById(R.id.SET);//セット数
        int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
        TextView NumberTime = (TextView) findViewById(R.id.timer);//回数
        int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
        TextView IntervalMiniute = (TextView) findViewById(R.id.IntervalMiniute);//インターバル分
        int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
        TextView IntervalSecond = (TextView) findViewById(R.id.interval2);//インターバル秒
        int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
        int numbertime = Int_NumberTime;
        int set = Int_TempoSetNumber;
        Check = 1;
        CountDownTimer.cancel();
        Intent intent = new Intent(this, MeasurementTempoActivity.class);
        intent.putExtra("Int_TempoSetNumber", set);
        intent.putExtra("Int_NumberTime", numbertime);
        intent.putExtra("Int_IntervalMiniute", Int_IntervalMiniute);
        intent.putExtra("Int_IntervalSecond", Int_IntervalSecond);
        startActivity(intent);
    }

    public void onClick1(View v) {
        CountDownTimer = new CountDownTimer(Miniute, 1000) {    //回数

            TextView TempoSetNumber = (TextView) findViewById(R.id.SET);//セット数
            int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
            TextView NumberTime = (TextView) findViewById(R.id.timer);//回数
            int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
            TextView IntervalMiniute = (TextView) findViewById(R.id.IntervalMiniute);//インターバル分
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            TextView IntervalSecond = (TextView) findViewById(R.id.interval2);//インターバル秒
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
            int numbertime = Int_NumberTime;
            int set = Int_TempoSetNumber;

            public void onTick(long millisUntilFinished) {
                //1秒毎に更新
                Miniute = millisUntilFinished;
               // if (Check == 1) {
                  //  CountDownTimer.cancel();
                //}
                if (numbertime != 0) {
                    numbertime--;
                    ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));//画面にカウントダウンの状態を表示
                } else if (numbertime == 0 && set != 0) {
                    set--;
                    ((TextView) findViewById(R.id.SET)).setText(String.valueOf(set));
                    numbertime = Int_NumberTime;
                    ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));
                    //  } else {
                    //    numbertime = Int_NumberTime;
                    //  ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));
                }
            }

            @Override
            public void onFinish() {

                if (set == 0 && numbertime == 0) {
                    Intent intent = new Intent(TimerSetCountActivity.this, ApplyCompleteActivity.class);
                    startActivity(intent);

                } else {
                    numbertime = Int_NumberTime;
                    ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));
                    if (Check == 0) {
                        Intent intent = new Intent(TimerSetCountActivity.this, TempoIntervalActivity.class);
                        intent.putExtra("Int_TempoSetNumber", set);
                        intent.putExtra("Int_NumberTime", numbertime);
                        intent.putExtra("Int_IntervalMiniute", Int_IntervalMiniute);
                        intent.putExtra("Int_IntervalSecond", Int_IntervalSecond);
                        startActivity(intent);
                    }
                }
            }
        }.start();
    }
}