package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity {
    //部品の変数
    EditText showDate;
    private TextView textView;
    private EditText editTextKey, editTextValue;
    private MyOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        //部品の取得
        showDate = (EditText) findViewById(R.id.showDate);

        //EditTextにリスナーをつける
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendarインスタンスを取得
                final Calendar date = Calendar.getInstance();

                //DatePickerDialogインスタンスを取得
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CalenderActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //setした日付を取得して表示
                                showDate.setText(String.format("%d / %02d / %02d", year, month+1, dayOfMonth));
                                searchData(db, year, month+1, dayOfMonth);
                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );

                //dialogを表示
                datePickerDialog.show();

            }
        });

        editTextKey = findViewById(R.id.edit_text_key);
        editTextValue = findViewById(R.id.edit_text_value);

        textView = findViewById(R.id.text_view);

        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "trainrecorddb",
                new String[] {"trainname", "setnum" ,"frequency","time"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append("  ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("セット  ");
            int judge = cursor.getInt(2);
            if(judge !=  0){
                sbuilder.append(cursor.getInt(2));
                sbuilder.append("回\n");
            }else {
                sbuilder.append(cursor.getInt(3));
                sbuilder.append("秒\n");
            }
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder.toString());
        textView.setText(sbuilder.toString());
    }

    public void onClick0(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    private void searchData(SQLiteDatabase db, int year, int month, int dayOfMonth) {
        if(helper == null){
            helper = new MyOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        String key = String.format("%d%02d%02d", year, month, dayOfMonth);
        Cursor cursor = db.query(
                "trainrecorddb",
                new String[] {"trainname", "setnum" ,"frequency","time"},
                "date = \"" + key + "\"",
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append("  ");
            sbuilder.append(cursor.getInt(1));
            sbuilder.append("セット  ");
            int judge = cursor.getInt(2);
            if(judge != 0){
                sbuilder.append(cursor.getInt(2));
                sbuilder.append("回\n");
            }else {
                sbuilder.append(cursor.getInt(3));
                sbuilder.append("秒\n");
            }

            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder.toString());
        textView.setText(sbuilder.toString());
    }
}