package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
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
    private TextView setNumber;
    private TextView tempoNumber;
    private TextView countNumber;
    private TextView intervalMiniute;
    private TextView intervalSecond;
    private AWSconnectTempo con;
    int Int_DBsetsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_tempo);

        //InputTrainNameActivityから受け取った値を表示します。
        Intent intent = getIntent();
        String Tempotrainname = intent.getStringExtra("TimerTrainName");
        boolean dispValues = intent.getBooleanExtra("ExistTrainName", false);
        TextView textview = (TextView) this.findViewById(R.id.Tempotrainname);
        Int_DBsetsend = intent.getIntExtra("DBSetSend",0);//////////////////////
        assert Tempotrainname != null;  //下のIF文に名前が入力されていることを意味する
        if (!Tempotrainname.equals("")) {//ここでは Trainname != null であることを想定している
            textview.setText(Tempotrainname);
            if (dispValues) {
                setNumber = findViewById(R.id.TempoSetNumber);
                tempoNumber = findViewById(R.id.TempoNumber);
                countNumber = findViewById(R.id.NumberTime);
                intervalMiniute = findViewById(R.id.IntervalMiniute);
                intervalSecond = findViewById(R.id.IntervalSecond);
                con = new AWSconnectTempo(setNumber, tempoNumber, countNumber, intervalMiniute, intervalSecond);//idをawsconnectに送る
                String URL = new String("http://13.113.228.107/ShowMeasurementTempoMET.php");//接続するphpファイルの決定
                String Values = new String("a=" + Tempotrainname);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
                con.execute(URL, Values);//第一引数にURL、第二引数以降にphpに送りたいのを入れる
            }

        } else {
            textview.setText("トレーニングが入力されていません");
        }
    }

    public void onClick0(View v) {//リセットボタン
        TextView Tempo = (TextView) findViewById(R.id.TempoNumber);
        Tempo.setText(String.valueOf(1));
        ((EditText) findViewById(R.id.NumberTime)).setText("" + 0);
        ((EditText) findViewById(R.id.TempoSetNumber)).setText("" + 0);
        ((EditText) findViewById(R.id.IntervalMiniute)).setText("" + 0);////
        ((EditText) findViewById(R.id.IntervalSecond)).setText("" + 0);////
    }

    public void onClick1(View v) {//スタート

        TextView textview = (TextView) findViewById(R.id.Tempotrainname);
        String String_trainname = textview.getText().toString();
        EditText TempoSetNumber = (EditText) findViewById(R.id.TempoSetNumber);//セット数
        EditText IntervalMiniute = (EditText) findViewById(R.id.IntervalMiniute);////////インターバル分///////////////////////////
        EditText IntervalSecond = (EditText) findViewById(R.id.IntervalSecond);//////////インターバル秒/////////////////////////
        EditText NumberTime = (EditText) findViewById(R.id.NumberTime);//回数
        TextView TempoNumber = (TextView) findViewById(R.id.TempoNumber);//テンポの値を取得

        if (TempoSetNumber.getText().toString().equals("") || IntervalMiniute.getText().toString().equals("")
                || IntervalSecond.getText().toString().equals("") || NumberTime.getText().toString().equals("")) {

        } else {

            int Int_TempoSetNumber = Integer.parseInt(TempoSetNumber.getText().toString());
            int Int_IntervalMiniute = Integer.parseInt(IntervalMiniute.getText().toString());
            int Int_IntervalSecond = Integer.parseInt(IntervalSecond.getText().toString());
            String String_Numbertime = NumberTime.getText().toString();
            int Int_TempoNumber = Integer.parseInt(TempoNumber.getText().toString());//Int型に変換
            int Int_NumberTime = Integer.parseInt(NumberTime.getText().toString());

            String set = TempoSetNumber.getText().toString();
            String front = IntervalMiniute.getText().toString();
            String back = IntervalSecond.getText().toString();
            String numbertime = NumberTime.getText().toString();

            if (Int_NumberTime == 0 || (Int_IntervalSecond == 0 && Int_IntervalMiniute == 0)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("回数または、インターバルが0になっています");
                builder.setMessage("0以外の数値を入力してください。");
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {

                Intent intent = new Intent(this, TimerSetCountActivity.class);
                intent.putExtra("TSCTrainName", String_trainname);
                intent.putExtra("Int_TempoSetNumber", set);
                intent.putExtra("Int_IntervalMiniute", front);
                intent.putExtra("Int_IntervalSecond", back);
                intent.putExtra("Int_NumberTime", numbertime);
                intent.putExtra("DBSetSend", Int_DBsetsend);
                intent.putExtra("TempoTempo", TempoNumber.getText().toString());
                startActivity(intent);
            }
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

