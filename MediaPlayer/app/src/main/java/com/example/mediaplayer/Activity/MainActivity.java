package com.example.mediaplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mediaplayer.Music.MusicFragment;
import com.example.mediaplayer.R;
import com.example.mediaplayer.Video.VideoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView musicTv,videoTv;
    public FrameLayout contentFl;
    public FragmentManager fm;
    public FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        musicTv = findViewById(R.id.music_tv);
        videoTv = findViewById(R.id.video_tv);
        contentFl = findViewById(R.id.content_fl);
        musicTv.setOnClickListener(this);
        videoTv.setOnClickListener(this);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        //默认初始展示界面为音乐姐界面。而不是视频界面
        ft.replace(R.id.content_fl,new MusicFragment());
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.music_tv:
                ft.replace(R.id.content_fl,new MusicFragment());
                break;
            case R.id.video_tv:
                ft.replace(R.id.content_fl,new VideoFragment());
                break;
        }
        ft.commit();
    }
}