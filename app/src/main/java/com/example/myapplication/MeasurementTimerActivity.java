package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MeasurementTimerActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    long IntervalTotal = 0;
    long Miniute = 0;
    int term = 0;
    int stopterm = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_timer);

        //InputTrainNameActivityから受け取った値を表示します。
        //InputTrainNameActivityから受け取った値を表示します。
        Intent intent = getIntent();
        String Trainname = intent.getStringExtra("InputTrainName");
        TextView Textview = (TextView) this.findViewById(R.id.TimerTrainName);
        assert Trainname != null;
        if (!Trainname.equals("")){//ここでは Trainname != null であることを想定している
            Textview.setText(Trainname);
        }else{
            Textview.setText("トレーニングが入力されていません");
        }
    }

    public void onClick0(View v) {//RESET
        ((EditText) findViewById(R.id.SetNumber)).setText("" + 0);
        ((EditText) findViewById(R.id.IntervalMiniute)).setText("" + 0);
        ((EditText) findViewById(R.id.IntervalSecond)).setText("" + 0);
        ((EditText) findViewById(R.id.TimerCountTimeMiniute)).setText("" + 0);
        ((EditText) findViewById(R.id.TimerCountTimeSecond)).setText("" + 0);
    }

    public void onClick1(View v) {
        stopterm = 1;
        EditText TimerSetNumber = (EditText) findViewById(R.id.SetNumber);//セット数
        int Int_TimerSetNumber = Integer.parseInt(TimerSetNumber.getText().toString());
        EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
        int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
        EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
        int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());
        EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
        int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
        EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間
        int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());

        if (Int_TimerCountTimeMiniute == 0 && Int_TimerCountTimeSecond == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("時間が設定されていません");
            builder.setMessage("時間を設定してください。");
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{

        Miniute = (Int_TimerCountTimeMiniute * 60 + Int_TimerCountTimeSecond) * 1000;
        IntervalTotal = (Int_TimerIntervalMiniute * 60 + Int_TimerIntervalSecond) * 1000;

        if (term == 0) {

            CountDownTimer = new CountDownTimer(Miniute, 1000) {    //時間
                EditText TimerSetNumber = (EditText) findViewById(R.id.SetNumber);//セット数
                int Int_TimerSetNumber = Integer.parseInt(TimerSetNumber.getText().toString());
                EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
                int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
                EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
                int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());
                EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
                int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
                EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間
                int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());
                int Mintime = 0;
                int Sectime = 0;
                int SEctime = 0;

                public void onTick(long millisUntilFinished) {
                    Miniute = millisUntilFinished / 1000;
                    int   Mintime = (int) millisUntilFinished / 60000;
                    int Sectime = (int) millisUntilFinished % 60000;
                    int SEctime = (int) Sectime / 1000;

                     ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText("" + Mintime);
                     ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText("" + SEctime);

                    if(Int_TimerSetNumber != 0 && Mintime == 0 && SEctime == 0){
                        Int_TimerSetNumber--;
                        Mintime = Int_TimerCountTimeMiniute;
                        SEctime = Int_TimerCountTimeSecond;
                        ((TextView) findViewById(R.id.SetNumber)).setText(String.valueOf(Int_TimerSetNumber));
                        ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText(String.valueOf(Mintime));
                        ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText(String.valueOf(SEctime));
                    } else if(Int_TimerSetNumber == 0 && Mintime == 0 && SEctime == 0){
                        Intent intent = new Intent(MeasurementTimerActivity.this, ApplyCompleteActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFinish() {
                  //  if (Int_TimerSetNumber == 0 && Mintime == 0 && SEctime == 0) {//さらにDBでポイントと比較
                    //    Intent intent = new Intent(MeasurementTimerActivity.this, ApplyCompleteActivity.class);
                      //  startActivity(intent);
                    //} else {

                        term = 1;
                        AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementTimerActivity.this);
                        builder.setTitle("1セットが終了しました");
                        builder.setMessage("スタートボタンでインターバルを開始します。");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        stopterm = 0;
                   // }
                }
            }.start();
            } else if (term == 1) {

            CountDownTimer = new CountDownTimer(IntervalTotal, 1000) {  //インターバル
                EditText TimerSetNumber = (EditText) findViewById(R.id.SetNumber);//セット数
                int Int_TimerSetNumber = Integer.parseInt(TimerSetNumber.getText().toString());
                EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
                int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
                EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
                int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());
                EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
                int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
                EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間
                int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());

                public void onTick(long millisUntilFinished) {
                    IntervalTotal = millisUntilFinished / 1000;
                    int Mt = (int) millisUntilFinished / 60000;
                    int St = (int) millisUntilFinished % 60000;
                    int ST = (int) St / 1000;
                    ((TextView) findViewById(R.id.IntervalMiniute)).setText("" + Mt);
                    ((TextView) findViewById(R.id.IntervalSecond)).setText("" + ST);
                }

                @Override
                public void onFinish() {
                    term = 0;
                    int mintime = Int_TimerIntervalMiniute;
                    int sectime = Int_TimerIntervalSecond;
                    ((TextView) findViewById(R.id.IntervalMiniute)).setText("" + mintime);
                    ((TextView) findViewById(R.id.IntervalSecond)).setText("" + sectime);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementTimerActivity.this);
                    builder.setTitle("インターバルを終了しました");
                    builder.setMessage("スタートボタンでタイマーを開始します。");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    stopterm = 0;
                }
            }.start();
        }
        }
    }
            public void onClick2(View v) {
                if (stopterm == 1) {
                    EditText TimerSetNumber = (EditText) findViewById(R.id.SetNumber);//セット数
                    int Int_TimerSetNumber = Integer.parseInt(TimerSetNumber.getText().toString());
                    EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
                    int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
                    EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
                    int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());
                    EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
                    int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
                    EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間
                    int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());
                    CountDownTimer.cancel();
                    ((EditText) findViewById(R.id.SetNumber)).setText("" + Int_TimerSetNumber);
                    ((EditText) findViewById(R.id.IntervalMiniute)).setText("" + Int_TimerIntervalMiniute);
                    ((EditText) findViewById(R.id.IntervalSecond)).setText("" + Int_TimerIntervalSecond);
                    ((EditText) findViewById(R.id.TimerCountTimeMiniute)).setText("" + Int_TimerCountTimeMiniute);
                    ((EditText) findViewById(R.id.TimerCountTimeSecond)).setText("" + Int_TimerCountTimeSecond);
                }
            }

            public void onClick3(View v) {
                TextView textview = (TextView) findViewById(R.id.TimerTrainName);
                String TimerTrainName = textview.getText().toString();//editText14の値を取り出す
                Intent intent = new Intent(this, MeasurementTempoActivity.class);
                intent.putExtra("TimerTrainName", TimerTrainName); //遷移先にこの情報をその名前で引き継ぐ
                startActivity(intent);
            }

    public void onClick4(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
