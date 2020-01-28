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
import android.renderscript.Sampler;
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
    private AWSconnectTimer con;
    private TextView text;
    private TextView setNumber;
    private TextView timerMiniute;
    private TextView timerSecond;
    private TextView intervalMiniute;
    private TextView intervalSecond;
    String Point = "29";
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    int spare[] = new int[4];
    int Int_DBsetsend;
    public int STOPTimerMin;
    public int STOPTimerSec;
    int termX = 0;
    private int timercoutminiute;
    private int timercountsec;
    public int Maxmin;
    public int Maxsec;

    private int timerIntervalmin;
    private int timerIntervalsec;
    public int STOPIntervalMin;
    public int STOPIntervalSec;

    public int Maxintervalmin;
    public int Maxintervalsec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_timer);

        Intent intent = getIntent();
        String Trainname = intent.getStringExtra("InputTrainName"); //InputTrainNameActivity,InputTrainNameActivityから受け取った値を表示
        boolean dispValues = intent.getBooleanExtra("ExistTrainName", false);

        Int_DBsetsend = intent.getIntExtra("DBSetSend",0);//////////////////////

        TextView Textview = (TextView) this.findViewById(R.id.TimerTrainName);
        assert Trainname != null;
        if (!Trainname.equals("")){//Trainname != null であることを想定している
            Textview.setText(Trainname);
            if (dispValues) {
                setNumber = findViewById(R.id.SetNumber);
                timerMiniute = findViewById(R.id.TimerCountTimeMiniute);
                timerSecond = findViewById(R.id.TimerCountTimeSecond);
                intervalMiniute = findViewById(R.id.IntervalMiniute);
                intervalSecond = findViewById(R.id.IntervalSecond);
                con = new AWSconnectTimer(setNumber, timerMiniute, timerSecond, intervalMiniute, intervalSecond);//idをawsconnectに送る
                String URL = new String("http://13.113.228.107/ShowMeasurementTimerMET.php");//接続するphpファイルの決定
                String Values = new String("a=" + Trainname);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
                con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいのを入れる
            }
        }else{
            Textview.setText("トレーニングが入力されていません");
        }

        helper = new MyOpenHelper(getApplicationContext()); //DB用宣言
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

    public void onClick1(View v) { //START

        EditText TimerSetNumber = (EditText) findViewById(R.id.SetNumber);//セット数
        EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
        EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
        EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
        EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間

        if(TimerSetNumber.getText().toString().equals("") || TimerIntervalMiniute.getText().toString().equals("")
                || TimerIntervalSecond.getText().toString().equals("") || TimerCountTimeMiniute.getText().toString().equals("")
                || TimerCountTimeSecond.getText().toString().equals("")) {

        }else {
            int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
            int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());
            int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
            int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());


            if((Int_TimerCountTimeMiniute != STOPTimerMin || Int_TimerCountTimeSecond != STOPTimerSec ||
                    Int_TimerIntervalMiniute != STOPIntervalMin || Int_TimerIntervalSecond != STOPIntervalSec )
            && (Int_TimerCountTimeMiniute != Maxmin || Int_TimerCountTimeSecond != Maxsec ||
                    Int_TimerIntervalMiniute != Maxintervalmin || Int_TimerIntervalSecond != Maxintervalsec)){

                STOPTimerMin = Int_TimerCountTimeMiniute;
                STOPTimerSec = Int_TimerCountTimeSecond;
                STOPIntervalMin = Int_TimerIntervalMiniute;
                STOPIntervalSec =Int_TimerIntervalSecond;
                termX = 0;
                term = 0;
            }

            if (stopterm == 0) {

                int Int_TimerSetNumber = Integer.parseInt(TimerSetNumber.getText().toString());

                if (termX == 0) {
                    timercoutminiute = Int_TimerCountTimeMiniute;
                    timercountsec = Int_TimerCountTimeSecond;
                    Maxmin = timercoutminiute;//げんぎょうの最高
                    Maxsec = timercountsec;

                    timerIntervalmin = Int_TimerIntervalMiniute;
                    timerIntervalsec = Int_TimerIntervalSecond;
                    Maxintervalmin = timerIntervalmin;
                    Maxintervalsec = timerIntervalsec;

                } else {

                    timercoutminiute = STOPTimerMin;
                    timercountsec = STOPTimerSec;

                    timerIntervalmin = STOPIntervalMin;
                    timerIntervalsec = STOPIntervalSec;
                }

                if (Int_TimerCountTimeMiniute == 0 && Int_TimerCountTimeSecond == 0) {//時間が0分0秒の時
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("時間が設定されていません");
                    builder.setMessage("時間を設定してください。");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else if (Int_TimerIntervalMiniute == 0 && Int_TimerIntervalSecond == 0) {//インターバルが0分0秒の時
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("インターバルが設定されていません");
                    builder.setMessage("インターバルを設定してください。");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    stopterm = 1;

                    Miniute = (timercoutminiute * 60 + timercountsec) * 1000 + 100;
                    IntervalTotal = (timerIntervalmin * 60 + timerIntervalsec) * 1000;

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


                            int FirstTime = Maxmin * 60 + Maxsec;//画面が切り替わる際に元の値に戻す用

                            public void onTick(long millisUntilFinished) {
                                int sparetime = (int) millisUntilFinished;//時間をカウントするための処理
                                int sparetime2 = (int) millisUntilFinished;
                                int a = 0;
                                for (int i = 0; i < 4; i++) {
                                    spare[a] = sparetime % 10;
                                    sparetime = sparetime / 10;
                                    a++;
                                }

                                int Mintime = sparetime2 / 60000;
                                int Sectime = sparetime2 % 60000;
                                int SEctime = Sectime / 1000;

                                if (5 <= spare[2] && spare[2] <= 9) {
                                    SEctime++;
                                }
                                if(SEctime == 60) {
                                    Sectime = 0;
                                }
                                //減らした秒数を表示
                                ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText(String.valueOf(Mintime));/////////////////
                                ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText(String.valueOf(SEctime));/////////////////更新される
                            }

                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onFinish() {
                                term = 1;
                                if (Int_TimerSetNumber != 0) {//セット数が残っている場合
                                    Int_TimerSetNumber--;
                                    Int_DBsetsend++;
                                    ((TextView) findViewById(R.id.SetNumber)).setText(String.valueOf(Int_TimerSetNumber));/////

                                    AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementTimerActivity.this);
                                    builder.setTitle("1セットが終了しました");
                                    builder.setMessage("スタートボタンでインターバルを開始します。");
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                    stopterm = 0;

                                } else {
                                    //履歴に登録する。
                                    ContentValues values = new ContentValues();
                                    Date date = new Date();
                                    SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
                                    String String_ymd = ymd.format(new Date());
                                    TextView Textview = (TextView) findViewById(R.id.TimerTrainName);
                                    String String_inputtrainname = Textview.getText().toString();
                                    values.put("date", String_ymd);
                                    values.put("trainname", String_inputtrainname);
                                    values.put("setnum", Int_DBsetsend + 1);
                                    values.put("time", FirstTime);

                                    db.insert("trainrecorddb", null, values);
                                    Intent intent = new Intent(MeasurementTimerActivity.this, MeasurementTimerEndActivity.class);
                                    startActivity(intent);
                                }

                                STOPTimerMin = Maxmin;
                                STOPTimerSec = Maxsec;

                                ((TextView) findViewById(R.id.TimerCountTimeMiniute)).setText(String.valueOf(Maxmin));/////////////////
                                ((TextView) findViewById(R.id.TimerCountTimeSecond)).setText(String.valueOf(Maxsec));/////////////////更新される
                                termX = 0;

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

                            public void onTick(long millisUntilFinished) {

                                int intMUF = (int) millisUntilFinished;
                                int intMUF2 = (int) millisUntilFinished;
                                int a = 0;
                                for (int i = 0; i < 4; i++) {
                                    spare[a] = intMUF % 10;
                                    intMUF = intMUF / 10;
                                    a++;
                                }

                                IntervalTotal = intMUF2 / 1000;
                                int Mt = intMUF2 / 60000;
                                int St = intMUF2 % 60000;
                                int ST = St / 1000;

                                if (5 <= spare[2] && spare[2] <= 9) {
                                    ST++;
                                }

                                ((TextView) findViewById(R.id.IntervalMiniute)).setText(String.valueOf(Mt));
                                ((TextView) findViewById(R.id.IntervalSecond)).setText(String.valueOf(ST));
                            }

                            @Override
                            public void onFinish() {
                                ((TextView) findViewById(R.id.IntervalMiniute)).setText(String.valueOf(0));
                                ((TextView) findViewById(R.id.IntervalSecond)).setText(String.valueOf(0));
                                term = 0;

                                AlertDialog.Builder builder = new AlertDialog.Builder(MeasurementTimerActivity.this);
                                builder.setTitle("インターバルを終了しました");
                                builder.setMessage("スタートボタンでタイマーを開始します。");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                stopterm = 0;

                                STOPIntervalMin = Maxintervalmin;
                                STOPIntervalSec = Maxintervalsec;

                                ((TextView) findViewById(R.id.IntervalMiniute)).setText(String.valueOf(Maxintervalmin));
                                ((TextView) findViewById(R.id.IntervalSecond)).setText(String.valueOf(Maxintervalsec));
                            }
                        }.start();
                    }
                }
            }
        }
    }

    public void onClick2(View v) {//STOP
        if (stopterm == 1) {
            CountDownTimer.cancel();
            stopterm = 0;

            EditText TimerCountTimeMiniute = (EditText) findViewById(R.id.TimerCountTimeMiniute);//時間
            int Int_TimerCountTimeMiniute = Integer.parseInt(TimerCountTimeMiniute.getText().toString());
            EditText TimerCountTimeSecond = (EditText) findViewById(R.id.TimerCountTimeSecond);//時間
            int Int_TimerCountTimeSecond = Integer.parseInt(TimerCountTimeSecond.getText().toString());
            EditText TimerIntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);//インターバル分
            int Int_TimerIntervalMiniute = Integer.parseInt(TimerIntervalMiniute.getText().toString());
            EditText TimerIntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//インターバル秒
            int Int_TimerIntervalSecond = Integer.parseInt(TimerIntervalSecond.getText().toString());

            STOPTimerMin = Int_TimerCountTimeMiniute;
            STOPTimerSec = Int_TimerCountTimeSecond;

            STOPIntervalMin = Int_TimerIntervalMiniute;
            STOPIntervalSec = Int_TimerIntervalSecond;

            termX = 1;
        }
    }

    public void onClick3(View v) {//計測テンポ画面に遷移
        if(stopterm == 1) {
            CountDownTimer.cancel();
            stopterm = 0;
        }
        TextView textview = (TextView) findViewById(R.id.TimerTrainName);
        String TimerTrainName = textview.getText().toString();//editText14の値を取り出す
        Intent intent = new Intent(this, MeasurementTempoActivity.class);
        intent.putExtra("TimerTrainName", TimerTrainName); //遷移先にこの情報をその名前で引き継ぐ //////////////
        startActivity(intent);
    }

    public void onClick4(View v) {//ホーム画面に遷移
        if (stopterm == 1) {
            CountDownTimer.cancel();
            stopterm = 0;
        }
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
