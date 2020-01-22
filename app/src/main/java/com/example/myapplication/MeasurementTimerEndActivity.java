package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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

       // Intent intent = getIntent();
       /* if (helper == null) {
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

        String judge = cursor.getString(0); //ここでメールアドレスを受け取った
        cursor.close();

        String judge = "12345@gmail.com";

        textview = (TextView) findViewById(R.id.PointNow);
        con = new AWSconnectSelect(textview, 3);//idをawsconnectに送る
        String URL = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String Mailaddres = new String("a=" + judge);//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(URL, Mailaddres);//第一引数にURL、第二引数以降にphpに送りたいのを入れる

        Date date = new Date();
        textview.setText((CharSequence) date);
*/
        TextView a = findViewById(R.id.PointNow);
        con = new AWSconnectSelect(a,3);//idをawsconnectに送る
        String DBe = new String("http://13.113.228.107/AccountInformationMET.php");//接続するphpファイルの決定
        String point = new String("a=12345@gmail.com");//androidstudioからphpに値を送る文字列(phpにはaと設定しているためa=XXXとする)
        con.execute(DBe, point);//第一引数にURL、第二引数以降にphpに送りたいものを入れる
    }

    public void onClick0(View v) {

      //  if ("29".equals(textview.getText().toString())) {
            //DBのポイント数を０に
            //上と同様に端末からメールアドレスをとる
            //フラグを立てる
            //String judge = "12345@gmail.com";
            //String URL = new String("http://13.113.228.107/PointUpdateMET.php");
           // String Mailaddres = new String("a=" + judge);
          //  execute(URL, Mailaddres, 0);//どうやってポイントを渡す？？

            Intent intent = new Intent(this, ApplyCompleteActivity.class);
            startActivity(intent);
       // } else {
            //DBのポイントを+1
         //   Intent intent = new Intent(this, InputTrainNameActivity.class);
           // startActivity(intent);
        //}
    }
        }
