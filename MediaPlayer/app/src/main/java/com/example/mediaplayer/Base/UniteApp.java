package com.example.mediaplayer.Base;

import android.app.Application;

import com.example.mediaplayer.DataBase.DBManger;

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManger.initDB(this);
    }
}
