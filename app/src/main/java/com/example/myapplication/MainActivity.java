package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private MyOpenHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick0(View v) {

        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "informationuserdb",
                new String[] { "mailaddress", "login" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

          int judge = cursor.getInt(1);



        if(judge == 0){
            cursor.close();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }else {
            cursor.close();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
