package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Button;
import java.util.Locale;

public class MeasurementTimerActivity extends AppCompatActivity {

    //筋トレ名を筋トレ名入力画面からよびだしているため、継承が必要かもしれない
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

    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        TextView  textview= (TextView) findViewById(R.id.TimerTrainName);
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
