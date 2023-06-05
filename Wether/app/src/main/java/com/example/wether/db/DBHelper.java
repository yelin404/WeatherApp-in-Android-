package com.example.wether.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "forecast.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表
        String sql = "create table info(id integer primary key autoincrement,city varchar(80) unique not null,content text not null)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
