package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME1 = "trainrecorddb";
    private static final String TABLE_NAME2 = "informationuserdb";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE1 = "date";
    private static final String COLUMN_NAME_TITLE2 = "trainname";
    private static final String COLUMN_NAME_TITLE3 = "setnum";
    private static final String COLUMN_NAME_TITLE4 = "frequency";
    private static final String COLUMN_NAME_TITLE5 = "time";

    private static final String COLUMN_NAME_TITLE6 = "mailaddress";
    private static final String COLUMN_NAME_TITLE7 = "login";


    private static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + TABLE_NAME1 + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE1 + " NUMERIC," +
                    COLUMN_NAME_TITLE2 + " TEXT," +
                    COLUMN_NAME_TITLE3 + " INTEGER," +
                    COLUMN_NAME_TITLE4 + " INTEGER," +
                    COLUMN_NAME_TITLE5 + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + TABLE_NAME1;


    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    COLUMN_NAME_TITLE6 + " TEXT PRIMARY KEY," +
                    COLUMN_NAME_TITLE7 + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + TABLE_NAME2;


    MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(SQL_DELETE_ENTRIES1);
        onCreate(db);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}