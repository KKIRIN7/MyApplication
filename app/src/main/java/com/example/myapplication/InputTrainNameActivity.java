package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputTrainNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_train_name);
    }

    public void onClick0(View v) {//筋トレ名が空であるか比較し、空であればエラー画面に推移、エラーでなければ、計測タイマー画面に遷移
        EditText editText = (EditText) findViewById(R.id.editText9);//editText14を指定
        String Inputtrainname = editText.getText().toString();//editText14の値を取り出す
        if (!Inputtrainname.equals("")) {//入力されているか判定　//入力されているとき
            //端末データベースに追加する
            Intent intent = new Intent(this, MeasurementTimerActivity.class);
            intent.putExtra("InputTrainName", Inputtrainname); //遷移先にこの情報をその名前で引き継ぐ
            startActivity(intent);//遷移
        } else{//入力されていない場合
            Intent intent = new Intent(this, InputTrainNameErrorActivity.class);
            startActivity(intent);
        }
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
