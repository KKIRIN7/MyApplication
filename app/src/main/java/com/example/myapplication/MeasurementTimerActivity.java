package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Date;

public class MeasurementTimerActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    long IntervalTotal = 0;
    long Miniute = 0;
    int term = 0;
    int stopterm = 0;
    private AWSconnect1 con;
    private TextView text;
    String Point = "29";
    private MyOpenHelper helper;
    private SQLiteDatabase db;

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

        helper = new MyOpenHelper(getApplicationContext());
        db = helper.getWritableDatabase();
    }

    public void onClick0(View v) {//RESET
        if (stopterm == 0) {
            ((EditText) findViewById(R.id.SetNumber)).setText("" + 0);
            ((EditText) findViewById(R.id.IntervalMiniute)).setText("" + 0);
            ((EditText) findViewById(R.id.IntervalSecond)).setText("" + 0);
            ((EditText) findViewById(R.id.TimerCountTimeMiniute)).setText("" + 0);
            ((EditText) findViewById(R.id.TimerCountTimeSecond)).setText("" + 0);
        }
    }

    public void onClick1(View v) {
        if (stopterm == 0) {
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
            } else {
                stopterm = 1;
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

                        int FirstTime = Int_TimerCountTimeMiniute * 60 + Int_TimerCountTimeSecond;

                        @RequiresApi(api = Build.VERSION_CODES.N)
                        public void onTick(long millisUntilFinished) {
                            Miniute = millisUntilFinished / 1000;
                            int Mintime = (int) millisUntilFinished / 60000;
                            int Sectime = (int) millisUntilFinished % 60000;
                            int SEctime = (int) Sectime / 1000;

                            ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText("" + Mintime);
                            ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText("" + SEctime);

                            if (Int_TimerSetNumber != 0 && Mintime == 0 && SEctime == 0) {
                                Int_TimerSetNumber--;
                                Mintime = Int_TimerCountTimeMiniute;
                                SEctime = Int_TimerCountTimeSecond;
                                ((TextView) findViewById(R.id.SetNumber)).setText(String.valueOf(Int_TimerSetNumber));
                                ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText(String.valueOf(Mintime));
                                ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText(String.valueOf(SEctime));
                            } else if (Int_TimerSetNumber == 0 && Mintime == 0 && SEctime == 0) {

                                //履歴に登録する。
                                    ContentValues values = new ContentValues();
                                    Date date = new Date();
                                    SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
                                    String String_ymd = ymd.format(new Date());
                                    //int Int_ymd = Integer.valueOf(String_ymd);
                                    TextView Textview = (TextView) findViewById(R.id.TimerTrainName);
                                    String String_inputtrainname = Textview.getText().toString();
                                    values.put("date", String_ymd);
                                    values.put("trainname", String_inputtrainname);
                                    values.put("setnum", Int_TimerSetNumber);
                                    //values.put("frequency");
                                    values.put("time", FirstTime);

                                   // String train = "e";
                                    //values.put("date", "20200120");
                                    //values.put("trainname", train);
                                    //values.put("setnum", 5);
                                    //values.put("time", 180);

                                    db.insert("trainrecorddb", null, values);
                                    Intent intent = new Intent(MeasurementTimerActivity.this, MeasurementTimerEndActivity.class);
                                    startActivity(intent);
                            }
                        }

                        @Override
                        public void onFinish() {

                            term = 1;
                            AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementTimerActivity.this);
                            builder.setTitle("1セットが終了しました");
                            builder.setMessage("スタートボタンでインターバルを開始します。");
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            stopterm = 0;
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
            stopterm = 0;
            term = 0;
        }
    }

    public void onClick3(View v) {
        if(term == 1) {
            CountDownTimer.cancel();
            stopterm = 0;
        }
        TextView textview = (TextView) findViewById(R.id.TimerTrainName);
        String TimerTrainName = textview.getText().toString();//editText14の値を取り出す
        Intent intent = new Intent(this, MeasurementTempoActivity.class);
        intent.putExtra("TimerTrainName", TimerTrainName); //遷移先にこの情報をその名前で引き継ぐ
        startActivity(intent);
    }

    public void onClick4(View v) {
        CountDownTimer.cancel();
        stopterm = 0;
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
