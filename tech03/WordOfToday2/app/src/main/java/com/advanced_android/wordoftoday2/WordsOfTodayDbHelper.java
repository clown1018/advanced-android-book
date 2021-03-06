package com.advanced_android.wordoftoday2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.advanced_android.wordoftoday2.WordsOfTodayContract.TABLE_NAME;
import static com.advanced_android.wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.DATE;
import static com.advanced_android.wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.NAME;
import static com.advanced_android.wordoftoday2.WordsOfTodayContract.WordsOfTodayColumns.WORDS;

/**
 * Created by stsutsui on 2015/11/01.
 */
public class WordsOfTodayDbHelper extends SQLiteOpenHelper {

    private static final String TAG = WordsOfToday.class.getSimpleName();
    public static final String DB_NAME = "WordsOfToday.db";
    public static final int DB_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            String.format("CREATE TABLE %s (\n", TABLE_NAME) +
            String.format("%s INTEGER PRIMARY KEY AUTOINCREMENT,\n", _ID) +
            String.format("%s TEXT,\n", NAME) +
            String.format("%s TEXT,\n", WORDS) +
            String.format("%s TEXT);", DATE);

    private static final String[] SQL_INSERT_INITIAL_DATA = {
            String.format("INSERT INTO %s (%s, %s, %s)" + "VALUES('Taiki','날씨 참 좋다','20151001')", TABLE_NAME, NAME, WORDS, DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Osamu','앱 버그 수정함','20151001')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Osamu','오늘도 앱 버그 잡기','20151002')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Taiki','열심히 운동함','20151002')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Ken','머리 짧게 깎음','20151002')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Taiki','오늘 점심 맛있네','20151003')",TABLE_NAME,NAME,WORDS,DATE),
            String.format("INSERT INTO %s (%s, %s, %s)"+"VALUES('Taiki','아침 4시 30분에 일어났다','20151004')",TABLE_NAME,NAME,WORDS,DATE),
    };

    public WordsOfTodayDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.beginTransaction();
        try {
            execSQL(db, SQL_CREATE_TABLE);
            for (String sql : SQL_INSERT_INITIAL_DATA) {
                execSQL(db, sql);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade oldVersion=" + oldVersion + ", newVersion=" + newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "onOpen");
    }

    private void execSQL(SQLiteDatabase db, String sql) {
        Log.d(TAG, "execSQL sql=" + sql);
        db.execSQL(sql);
    }
}
