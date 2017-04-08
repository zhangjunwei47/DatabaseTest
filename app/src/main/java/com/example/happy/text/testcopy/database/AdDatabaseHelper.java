package com.example.happy.text.testcopy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.happy.text.testcopy.StringUtil;

public class AdDatabaseHelper extends SQLiteOpenHelper {
    public static final String KEY_BOOK_NAME = "name";
    public static final String KEY_BOOK_PRICE = "price";
    public static final String KEY_BOOK_AUTHOR = "author";
    public static final String DATA_BASE_NAME = "book_xx";
    public static final int VERSION = 1;

    AdDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlStr = StringUtil.join("create table ", DATA_BASE_NAME + "(", KEY_BOOK_NAME, " TEXT primary key not null, ", KEY_BOOK_PRICE, " TEXT, ", KEY_BOOK_AUTHOR, " TEXT)");
        sqLiteDatabase.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
