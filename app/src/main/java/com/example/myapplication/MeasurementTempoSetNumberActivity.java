package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MeasurementTempoSetNumberActivity extends AppCompatActivity {

    long Miniute = 0;
    CountDownTimer CountDownTimer;
    int Check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_tempo_set_number);

        Intent intent = getIntent();
        String TempoSetNumber = intent.getStringExtra("Int_TempoSetNumber");//何の名前で受け渡してきたか
        TextView textview = (TextView) this.findViewById(R.id.TempoSetNumber); //このアクティビティ内で受け取るBOX
        String IntervalMiniute = intent.getStringExtra("Int_IntervalMiniute");//何の名前で受け渡してきたか
        TextView textview2 = (TextView) this.findViewById(R.id.TempoMinute); //このアクティビティ内で受け取るBOX
        String IntervalSecond = intent.getStringExtra("Int_IntervalSecond");//何の名前で受け渡してきたか
        TextView textview3 = (TextView) this.findViewById(R.id.TempoSecond); //このアクティビティ内で受け取るBOX
        String NumberTime = intent.getStringExtra("Int_NumberTime");//何の名前で受け渡してきたか
        TextView textview4 = (TextView) this.findViewById(R.id.NumberTime); //このアクティビティ内で受け取るBOX
    }
    public void onClick0(View v) {//リセットボタン
        TextView Tempo = (TextView) findViewById(R.id.TempoNumber);
        Tempo.setText(String.valueOf(1));
        ((EditText) findViewById(R.id.NumberTime)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoSetNumber)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoMinute)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoSecond)).setText("" + 0);
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

        String set = TempoSetNumber.getText().toString();
        String front = IntervalMiniute.getText().toString();
        String back = IntervalSecond.getText().toString();
        String numbertime = NumberTime.getText().toString();
        Miniute = (Int_NumberTime + 1) * 1000;

        CountDownTimer = new CountDownTimer(Miniute, 1000) {    //回数
            TextView TempoSetNumber = (TextView) findViewById(R.id.TempoSetNumber);//セット数
            int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
            TextView NumberTime = (TextView) findViewById(R.id.NumberTime);//回数
            int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
            TextView IntervalMiniute = (TextView) findViewById(R.id.TempoMinute);//インターバル分
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            TextView IntervalSecond = (TextView) findViewById(R.id.TempoSecond);//インターバル秒
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
            int numbertime = Int_NumberTime;
            int set = Int_TempoSetNumber;

            public void onTick(long millisUntilFinished) {
                //1秒毎に更新
                Miniute = millisUntilFinished;
                if (Check == 1) {
                    CountDownTimer.cancel();
                }
                if (numbertime != 0) {
                    numbertime--;
                    ((TextView) findViewById(R.id.NumberTime)).setText(String.valueOf(numbertime));//画面にカウントダウンの状態を表示
                } else if (numbertime == 0 && set != 0) {
                    set--;
                    ((TextView) findViewById(R.id.TempoSetNumber)).setText(String.valueOf(set));
                    numbertime = Int_NumberTime;
                    ((TextView) findViewById(R.id.NumberTime)).setText(String.valueOf(numbertime));
                    //  } else {
                    //    numbertime = Int_NumberTime;
                    //  ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));
                }
            }

            @Override
            public void onFinish() {
                if (set == 0 && numbertime == 0) {//さらにDBでポイントと比較
                    Intent intent = new Intent(MeasurementTempoSetNumberActivity.this,  ApplyCompleteActivity.class);
                    startActivity(intent);
                } else {
                    numbertime = Int_NumberTime;
                    ((TextView) findViewById(R.id.NumberTime)).setText(String.valueOf(numbertime));
                }
                if (Check == 0) {
                    Intent intent = new Intent(MeasurementTempoSetNumberActivity.this, TempoIntervalActivity.class);
                    intent.putExtra("Int_TempoSetNumber", set);
                    intent.putExtra("Int_NumberTime", numbertime);
                    intent.putExtra("Int_IntervalMiniute", Int_IntervalMiniute);
                    intent.putExtra("Int_IntervalSecond", Int_IntervalSecond);
                    startActivity(intent);
                }
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
}
