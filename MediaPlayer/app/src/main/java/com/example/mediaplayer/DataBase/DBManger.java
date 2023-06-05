package com.example.mediaplayer.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManger {
    public static SQLiteDatabase database;

    //初始化数据库，其实就是打开数据库，如果没有就创建一个数据库
    public static void initDB(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //接下来的就是数据库最基本的功能：增删改查

    //向数据库中增加一条数据
    public static long insertUserInfo(String account,String password) {
        ContentValues values = new ContentValues();
        values.put("account",account);
        values.put("password",password);
        return database.insert("user",null,values);
    }

    //数据库删除一条信息，注意此时时账号密码同步删除！！
    public static int deleteUserInfo(String account) {
        return database.delete("user","account=?",new String[]{account});
    }

    //改变数据库中某一个账号密码
    public static int updateUserInfo(String account,String password) {
        ContentValues values = new ContentValues();
        values.put("account",account);
        values.put("password",password);
        return database.update("user",values,"account=?",new String[]{account});
    }

    //根据账号名字查询密码
    public static String getPasswod(String account) {
        Cursor cursor = database.query("user", null, "account=?", new String[]{account}, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            return password;
        }
        return null;
    }

    //删除所有信息
    public static void deleteAll() {
        database.delete("user",null,null);
    }

    //获取数据库中账号数量
    public static int getCount() {
        Cursor cursor = database.query("user", null, null, null, null, null, null);
        return cursor.getCount();
    }

    //判断账号是否存在，如果存在返回1，不存在返回0
    public static int isExist(String account) {
        Cursor cursor = database.query("user", null, "account=?", new String[]{account}, null, null, null);

        if (cursor.getCount() > 0 && cursor != null) {
            return 1;
        }
        return 0;

    }
}
