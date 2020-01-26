package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class TimerSetCountActivity extends AppCompatActivity {
    private TextView timerText;
    long Miniute = 0;
    CountDownTimer CountDownTimer;
    int Check = 0;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    private SoundPool soundPool;
    private int Sound;

    public void play_Sound(){soundPool.play(Sound,1f , 1f, 0, 0, 1);};//音の宣言

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_set_count);

        Intent intent = getIntent();
        String trainname = intent.getStringExtra("TSCTrainName");
        TextView textview = (TextView) this.findViewById(R.id.trainname);
        String TempoSetNumber = intent.getStringExtra("Int_TempoSetNumber");//何の名前で受け渡してきたか
        TextView textview1 = (TextView) this.findViewById(R.id.SET); //このアクティビティ内で受け取るBOX
        String IntervalMiniute = intent.getStringExtra("Int_IntervalMiniute");//何の名前で受け渡してきたか
        TextView textview2 = (TextView) this.findViewById(R.id.IntervalMiniute); //このアクティビティ内で受け取るBOX
        String IntervalSecond = intent.getStringExtra("Int_IntervalSecond");//何の名前で受け渡してきたか
        TextView textview3 = (TextView) this.findViewById(R.id.interval2); //このアクティビティ内で受け取るBOX
        String NumberTime = intent.getStringExtra("Int_NumberTime");//何の名前で受け渡してきたか
        TextView textview4 = (TextView) this.findViewById(R.id.timer); //このアクティビティ内で受け取るBOX
        String Tempo = intent.getStringExtra("TempoTempo");//何の名前で受け渡してきたか

        textview.setText(trainname);
        textview1.setText(String.valueOf(TempoSetNumber));
        textview2.setText(String.valueOf(IntervalMiniute));
        textview3.setText(String.valueOf(IntervalSecond));
        textview4.setText(String.valueOf(NumberTime));
        int Int_NumberTime = Integer.parseInt(textview4.getText().toString());

        Miniute = (Int_NumberTime + 1) * 1000;
        helper = new MyOpenHelper(getApplicationContext());
        db = helper.getWritableDatabase();
        float f = Float.parseFloat(Tempo);

        Float Float_Tempo = (3 * f + 5) / 16;//テンポ数によって速さを変える

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            //1個目のパラメーターはリソースの数に合わせる
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    //パラメーターはリソースの数に合わせる
                    .setMaxStreams(1)
                    .build();
        }
        soundPool.play(Sound,1f,1f,0,0, Float_Tempo);
        Sound = soundPool.load(this, R.raw.ka2, 1); //音を変える rawファイルに入ってるのが該当
    }

    public void onClick0(View v) {//バツボタン

        TextView trainname = (TextView) findViewById(R.id.trainname);
        String String_trainname = trainname.getText().toString();
        TextView TempoSetNumber = (TextView) findViewById(R.id.SET);//セット数
        int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
        TextView NumberTime = (TextView) findViewById(R.id.timer);//回数
        int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
        TextView IntervalMiniute = (TextView) findViewById(R.id.IntervalMiniute);//インターバル分
        int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
        TextView IntervalSecond = (TextView) findViewById(R.id.interval2);//インターバル秒
        int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());

        if(Check == 1) {
            CountDownTimer.cancel();
            soundPool.stop(Sound);
        }
        Intent intent = new Intent(this, MeasurementTempoActivity.class);
        intent.putExtra("TimerTrainName", String_trainname);
        intent.putExtra("Int_TempoSetNumber", Int_TempoSetNumber);
        intent.putExtra("Int_NumberTime", Int_NumberTime);
        intent.putExtra("Int_IntervalMiniute", Int_IntervalMiniute);
        intent.putExtra("Int_IntervalSecond", Int_IntervalSecond);
        startActivity(intent);
    }

    public void onClick1(View v) {//START
        Check = 1;

        CountDownTimer = new CountDownTimer(Miniute, 1000) {    //回数

            TextView trainname = (TextView) findViewById(R.id.trainname);
            String String_trainname = trainname.getText().toString();
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

            int FirstTime = Int_IntervalMiniute * 60 + Int_IntervalSecond;

            public void onTick(long millisUntilFinished) {
                //1秒毎に更新
                Miniute = millisUntilFinished;

                if (numbertime != 0) {
                    play_Sound(); //音楽を呼び出す
                    numbertime--;
                    ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));//画面にカウントダウンの状態を表示
                } else if (numbertime == 0 && set != 0) {
                    set--;
                    ((TextView) findViewById(R.id.SET)).setText(String.valueOf(set));
                    numbertime = Int_NumberTime;
                    ((TextView) findViewById(R.id.timer)).setText(String.valueOf(numbertime));//セット数が減った後、回数を初期値に戻す
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {

                if (set == 0 && numbertime == 0) {//端末DBにトレーニング状況を保存

                    ContentValues values = new ContentValues();
                    Date date = new Date();
                    SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
                    String String_ymd = ymd.format(new Date());
                    TextView Textview = (TextView) findViewById(R.id.trainname);
                    String String_inputtrainname = Textview.getText().toString();
                    values.put("date", String_ymd);
                    values.put("trainname", String_inputtrainname);
                    values.put("setnum", Int_TempoSetNumber);
                    values.put("frequency", Int_NumberTime);
                    values.put("time", FirstTime);
                    db.insert("trainrecorddb", null, values);

                    Intent intent = new Intent(TimerSetCountActivity.this, MeasurementTimerEndActivity.class);
                    startActivity(intent);

                } else {

                        Intent intent = new Intent(TimerSetCountActivity.this, TempoIntervalActivity.class);
                        intent.putExtra("TIATrainName", String_trainname);
                        intent.putExtra("Int_TempoSetNumber", set);
                        intent.putExtra("Int_NumberTime", numbertime);
                        intent.putExtra("Int_IntervalMiniute", Int_IntervalMiniute);
                        intent.putExtra("Int_IntervalSecond", Int_IntervalSecond);
                        startActivity(intent);
                }
            }
        }.start();
    }
}