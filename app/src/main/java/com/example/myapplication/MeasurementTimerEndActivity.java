package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MeasurementTimerEndActivity extends AppCompatActivity {
    private AWSconnectSelect con;
    private MyOpenHelper helper;
    private SQLiteDatabase db;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_timer_end);

        //DBの呼び出し
        if (helper == null) {
            helper = new MyOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");

        Cursor cursor = db.query(
                "informationuserdb",
                new String[]{"mailaddress", "login"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String mymail = cursor.getString(0);
        cursor.close();

        textview = (TextView) findViewById(R.id.PointNow);
        con = new AWSconnectSelect(textview, 3);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String Mailaddres = new String("a=" + mymail);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(URL, Mailaddres);//第一引数にURL、第二引数以降にphpに送りたいのを入れる

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick0(View v) {
        //ここからメールアドレスの参照//
        if (helper == null) {
            helper = new MyOpenHelper(getApplicationContext());
        }
        if (db == null) {
            db = helper.getReadableDatabase();
        }

        Cursor cursor = db.query(
                "informationuserdb",
                new String[]{"mailaddress", "login"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String mymail = cursor.getString(0);
        cursor.close();

        textview = (TextView) findViewById(R.id.PointNow);
        con = new AWSconnectSelect(textview, 3);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String Mailaddres = new String("a=" + mymail);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(URL, Mailaddres);//第一引数にURL、第二引数以降にphpに送りたいのを入れる

        //ここまでメールアドレスの参照//
        //ここから日付の参照//
        if (helper == null) {
            helper = new MyOpenHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }

        SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
        String String_ymd = ymd.format(new Date());

        Cursor cursor1 = db.query(
                "trainrecorddb",
                new String[]{"date"},
                "date = \"" + String_ymd + "\"",
                null,
                null,
                null,
                null
        );
        cursor1.moveToFirst();
        int judge = cursor1.getCount();
        cursor1.close();
        //ここまで日付の参照//
        //ここからポイントの追加を行う処理//
        if (judge == 1) {//今日とDB内の日付

            if ("29".equals(textview.getText().toString())) { //BDから帰ってきたポイントと29を比較（表示されている）
                //DBのポイント数を０に
                String String_point = "30";
                con = new AWSconnectSelect(textview, 3);//idをawsconnectSelectに送る
                String URL1 = new String("http://13.113.228.107/PointUpdateMET.php");//接続するphpファイルの決定
                String Mailaddres1 = new String("a=" + mymail + "&b="+String_point);
                con.execute(URL1, Mailaddres1);//第一引数にURL、第二引数以降にphpに送りたいのを入れる*/

                Intent intent = new Intent(this, ApplyCompleteActivity.class);
                startActivity(intent);

            } else {
                //DBのポイントを+1
                int Int_Point = Integer.parseInt(textview.getText().toString());//Int型に変換
                int addPoint = Int_Point + 1;
                String String_Point = Integer.toString(addPoint);

                con = new AWSconnectSelect(textview, 3);//idをawsconnectに送る
                String URL2 = new String("http://13.113.228.107/PointUpdateMET.php");//接続するphpファイルの決定
                String Mailaddres2 = new String("a=" + mymail + "&b="+String_Point);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
                con.execute(URL2, Mailaddres2);//第一引数にURL、第二引数以降にphpに送りたいのを入れる*/

                Intent intent = new Intent(this, InputTrainNameActivity.class);
                startActivity(intent);
            }
        } else {//既に同じ日にポイントの加算がされている
            Intent intent = new Intent(this, InputTrainNameActivity.class);
            startActivity(intent);
        }
    }
}
