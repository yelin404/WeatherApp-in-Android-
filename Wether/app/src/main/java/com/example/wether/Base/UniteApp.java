package com.example.wether.Base;

import android.app.Application;

import com.example.wether.db.DBManager;

import org.xutils.x;

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        DBManager.initDB(this);


    }
}
