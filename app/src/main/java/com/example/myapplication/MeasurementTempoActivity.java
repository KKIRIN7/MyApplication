package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MeasurementTempoActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    long time = 10000;//10秒
    long Miniute = 0;
    long Miniute2 = 0;
    static int term = 1;
    static int term2 = 0;
    int Timeminview = 0;
    int Timesecview = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_tempo);

        //InputTrainNameActivityから受け取った値を表示します。
        Intent intent = getIntent();
        String Tempotrainname = intent.getStringExtra("TimerTrainName");
        TextView textview = (TextView) this.findViewById(R.id.Tempotrainname);
        assert Tempotrainname != null;  //下のIF文に名前が入力されていることを意味する
        if (!Tempotrainname.equals("")) {//ここでは Trainname != null であることを想定している
            textview.setText(Tempotrainname);
        } else {
            textview.setText("トレーニングが入力されていません");
        }
    }

    public void onClick0(View v) {//リセットボタン
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {//スタート
        EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
        int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
        EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
        int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
        EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
        int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
        EditText NumberTime = (EditText) findViewById(R.id.NumberTime);//回数
        int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
        TextView TempoNumber = (TextView) findViewById(R.id.TempoNumber);//テンポの値を取得
        int Int_TempoNumber = Integer.parseInt(TempoNumber.getText().toString());//Int型に変換
        int numbertime = Int_NumberTime;
        int set = Int_TempoSetNumber;
        int front = Int_IntervalMiniute;
        int back = Int_IntervalSecond;

        Miniute = (numbertime + 1) * 1000;
        Miniute2 = (front * 60 + back) * 1000; //タイマーの数カウントダウンする
        int Second = (Int_TempoNumber / 10) * 10;

        //for(count = 0; count < set + 1; count++) {
        //  ((TextView) findViewById(R.id.textView3)).setText("" + count);

        // if (term == 1) {
        //   cd();
        // }else if (term2 == 1) {
        //   interval();
        /// }
        //}

        CountDownTimer countDownTimer = new CountDownTimer(Miniute, 1000) {    //回数
            EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
            int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
            EditText NumberTime = (EditText) findViewById(R.id.NumberTime);//回数
            int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
            EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
            int numbertime = Int_NumberTime;
            int set = Int_TempoSetNumber;

            @Override
            public void onTick(long millisUntilFinished) {
                //1秒毎に更新
                Miniute = millisUntilFinished;
                if (numbertime != 0) {
                    numbertime--;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);//画面にカウントダウンの状態を表示
                } else if (numbertime == 0 && set != 0) {
                    set--;
                    ((EditText) findViewById(R.id.TempoSetNumber)).setText("" + set);
                    numbertime = Int_NumberTime;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);
                } else {
                    numbertime = Int_NumberTime;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);
                }
            }

            @Override
            public void onFinish() {
                //finish();この画面遷移から抜け
                //                }
                //    }.start();

                CountDownTimer countDownTimer2 = new CountDownTimer(Miniute2, 100) {    //インターバル
                    EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
                    int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
                    EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
                    int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
                    EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
                    int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
                    int set = Int_TempoSetNumber;

                    @Override
                    public void onTick(long millisUntilFinished) {
                        Miniute2 = millisUntilFinished;
                        int Mint = (int) millisUntilFinished / 60000;
                        int SEct = (int) millisUntilFinished % 60000;
                        int Sect = (int) SEct / 1000;
                        ((EditText) findViewById(R.id.TempoMinute)).setText("" + Mint);
                        ((EditText) findViewById(R.id.TempoSecond)).setText("" + Sect);
                    }

                    @Override
                    public void onFinish() {
                        ((EditText) findViewById(R.id.TempoMinute)).setText("" + Int_IntervalMiniute);
                        ((EditText) findViewById(R.id.TempoSecond)).setText("" + Int_IntervalSecond);
                    }
                }.start();
            }
        }.start();
    }

    public void onClick2(View v) {//ストップボタン
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        //テンポカウントダウン
        TextView TempoNumber = (TextView) findViewById(R.id.TempoNumber);
        int TempoNumberNow = Integer.parseInt(TempoNumber.getText().toString());
        if (TempoNumberNow != 1) {
            int TempoCount = 1;
            int TempoNumberAfter = TempoNumberNow - TempoCount;
            TextView TempoNumberAfter1 = (TextView) findViewById(R.id.TempoNumber);
            TempoNumberAfter1.setText(String.valueOf(TempoNumberAfter));
        }
    }

    public void onClick4(View v) {
        //テンポカウントUP
        TextView TempoNumber = (TextView) findViewById(R.id.TempoNumber);
        int TempoNumberNow = Integer.parseInt(TempoNumber.getText().toString());
        if (TempoNumberNow != 9) {
            int TempoCount = 1;
            int TempoNumberAfter = TempoNumberNow + TempoCount;
            TextView TempoNumberAfter1 = (TextView) findViewById(R.id.TempoNumber);
            TempoNumberAfter1.setText(String.valueOf(TempoNumberAfter));
        }
    }

    public void onClick5(View v) {
        TextView textview = (TextView) findViewById(R.id.Tempotrainname);
        String TempoTrainName = textview.getText().toString();
        Intent intent = new Intent(this, MeasurementTimerActivity.class);
        intent.putExtra("InputTrainName", TempoTrainName);
        startActivity(intent);
    }

    public void onClick6(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /*
    public void cd() {

        android.os.CountDownTimer countDownTimer = new CountDownTimer(Miniute, 1000) {    //回数
            EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
            int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
            EditText NumberTime = (EditText) findViewById(R.id.NumberTime);//回数
            int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
            EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
            int numbertime = Int_NumberTime;
            int set = Int_TempoSetNumber;

            @Override
            public void onTick(long millisUntilFinished) {
                //1秒毎に更新
                Miniute = millisUntilFinished;
                if (numbertime != 0) {
                    numbertime--;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);//画面にカウントダウンの状態を表示
                } else if (numbertime == 0 && set != 0) {
                    set--;
                    ((EditText) findViewById(R.id.TempoSetNumber)).setText("" + set);
                    numbertime = Int_NumberTime;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);
                } else {
                    numbertime = Int_NumberTime;
                    ((EditText) findViewById(R.id.NumberTime)).setText("" + numbertime);
                }
            }

            @Override
            public void onFinish() {
             //   term = 0;
               // term2 =1;
            }

        }.start();
    }

    public void interval() {

        CountDownTimer countDownTimer2 = new CountDownTimer(Miniute2, 100) {    //インターバル
            EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());

            @Override
            public void onTick(long millisUntilFinished) {
                Miniute2 = millisUntilFinished;
                int Mint = (int) millisUntilFinished / 60000;
                int SEct = (int) millisUntilFinished % 60000;
                int Sect = (int) SEct / 1000;
                ((EditText) findViewById(R.id.TempoMinute)).setText("" + Mint);
                ((EditText) findViewById(R.id.TempoSecond)).setText("" + Sect);
            }

            @Override
            public void onFinish() {
                ((EditText) findViewById(R.id.TempoMinute)).setText("" + Int_IntervalMiniute);
                ((EditText) findViewById(R.id.TempoSecond)).setText("" + Int_IntervalSecond);
                //term = 1;
                //term2 = 0;
            }
        }.start();
    }
*/}

