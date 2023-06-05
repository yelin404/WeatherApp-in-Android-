package com.example.wether.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public static SQLiteDatabase database;

    //初始化数据库，其实就是打开数据库，如果没有就创建一个数据库
    public static void initDB(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //接下来的就是数据库最基本的功能：增删改查

    //向数据库中增加一条数据
    public static long insertCityInfo(String city,String content) {
        ContentValues values = new ContentValues();
        values.put("city",city);
        values.put("Content",content);
        return database.insert("info",null,values);
    }

    //删除数据库中的一条数据,注意，这里删除两个东西：城市名称和与城市有关的天气信息
    public static int  deleteCityInfo(String city) {
        return database.delete("info","city=?",new String[]{city});
    }

    //改变数据库中某一个城市的天气信息
    public static int updateCityInfo(String city,String content) {
        ContentValues values = new ContentValues();
        values.put("city",city);
        values.put("content",content);
        return database.update("info",values,"city=?",new String[]{city});
    }

    //根据城市名称查询数据库中的信息
    public static String queryCityInfo(String city) {
        Cursor cursor = database.query("info",null,"city=?",new String[]{city},null,null,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

            return content;
        }
        return null;
    }

    //获取数据库中的全部信息
    public static List<DataBaseBean> getAllInfo(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<DataBaseBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            DataBaseBean dataBaseBean = new DataBaseBean(city,content,id);
            list.add(dataBaseBean);
        }
        return list;
    }

    //删除数据库中的所有信息
    public static void deleteAll() {
        database.delete("info",null,null);
    }

    //获取数据库中的所有城市名称，并通过列表返回
    public static List<String> getAllCityName() {
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<String>cityList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            cityList.add(city);
        }
        return cityList;
    }

    //获取数据库中城市的数量
    public static int getCityCount() {
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        return cursor.getCount();
    }


}
