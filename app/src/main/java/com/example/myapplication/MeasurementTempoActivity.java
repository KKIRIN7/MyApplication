package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MeasurementTempoActivity extends AppCompatActivity {

    CountDownTimer CountDownTimer;
    long time = 10000;//10秒
    long Miniute = 0;
    long Miniute2 = 0;
    int term = 1;
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
        TextView Tempo = (TextView) findViewById(R.id.TempoNumber);
        Tempo.setText(String.valueOf(1));
        ((EditText) findViewById(R.id.NumberTime)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoSetNumber)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoMinute)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoSecond)).setText("" + 0);
    }

    public void onClick1(View v) {//スタート

        TextView textview = (TextView) findViewById(R.id.Tempotrainname);
        String String_trainname = textview.getText().toString();

        EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
        int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
        EditText IntervalMiniute = (EditText) findViewById(R.id.TempoMinute);//インターバル分
        int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
        EditText IntervalSecond = (EditText) findViewById(R.id.TempoSecond);//インターバル秒
        int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
        EditText NumberTime = (EditText) findViewById(R.id.NumberTime);//回数
        String String_Numbertime = NumberTime.getText().toString();
       /* if (String_Numbertime == ""){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("回数が入力されていません");
            builder.setMessage("回数を入力してください。");
            AlertDialog dialog = builder.create();
            dialog.show();
            Intent intent1 = new Intent(this, MeasurementTempoActivity.class);
            intent1.putExtra("TimerTrainName",String_trainname);
        }
*/
        int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());
        TextView TempoNumber = (TextView) findViewById(R.id.TempoNumber);//テンポの値を取得
        int Int_TempoNumber = Integer.parseInt(TempoNumber.getText().toString());//Int型に変換

        String set = TempoSetNumber.getText().toString();
        String front = IntervalMiniute.getText().toString();
        String back = IntervalSecond.getText().toString();
        String numbertime = NumberTime.getText().toString();

        if (Int_NumberTime == 0 ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("回数が０になっています");
            builder.setMessage("回数を0以外の値に設定してください。");
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {

            Intent intent = new Intent(this, TimerSetCountActivity.class);
            intent.putExtra("TSCTrainName", String_trainname);
            intent.putExtra("Int_TempoSetNumber", set);
            intent.putExtra("Int_IntervalMiniute", front);
            intent.putExtra("Int_IntervalSecond", back);
            intent.putExtra("Int_NumberTime", numbertime);
            intent.putExtra("TempoTempo", TempoNumber.getText().toString());
            startActivity(intent);
        }
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

