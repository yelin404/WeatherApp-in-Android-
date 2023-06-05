package com.example.mediaplayer.Video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mediaplayer.R;
import static com.example.mediaplayer.Bean.Constant.songName;
import  static com.example.mediaplayer.Bean.Constant.songPic;
import static com.example.mediaplayer.Bean.Constant.videoPic;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView exitIv;
    private VideoView videoVv;
    private Button pauseBtn,playBtn,continueBtn,preBtn,nextBtn;
    private MediaController controller;
    private TextView videoNameTv;
    private int change = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //初始化组件
        initView();
    }

    private void initView() {
        exitIv = findViewById(R.id.exit_Iv);
        videoVv = findViewById(R.id.video_vv);
        pauseBtn = findViewById(R.id.pause_btn);
        playBtn = findViewById(R.id.play_btn);
        continueBtn = findViewById(R.id.btn_continue_play);
        preBtn = findViewById(R.id.pre_btn);
        nextBtn = findViewById(R.id.next_btn);
        controller = new MediaController(this);
        videoVv = findViewById(R.id.video_vv);
        videoNameTv = findViewById(R.id.video_name_tv);

        //绑定点击事件
        exitIv.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        preBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        videoVv.setMediaController(controller);


    }

    public void videoPrepare() {
        //获取传进来德position，这个值表示的是用户点击了哪一个视频
        Intent intent = getIntent();
        int position = Integer.parseInt(intent.getStringExtra("position"));
        position += change;

        videoNameTv.setText(songName[position]);

        //让软件访问服务器并下载视频吧
        Uri uri = Uri.parse("http://192.168.0.101:8080/video/video" + position + ".mp4");
        videoVv.setVideoURI(uri);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.exit_Iv:
                videoVv.pause();
                finish();
                break;
            case R.id.play_btn:
                playBtn.setVisibility(View.INVISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                videoPrepare();
                videoVv.start();
                break;
            case R.id.pause_btn:
                pauseBtn.setVisibility(View.INVISIBLE);
                continueBtn.setVisibility(View.VISIBLE);
                videoVv.pause();
                break;
            case R.id.btn_continue_play:
                pauseBtn.setVisibility(View.VISIBLE);
                continueBtn.setVisibility(View.INVISIBLE);
                videoVv.start();
                break;
            case R.id.pre_btn:
                change--;
                videoPrepare();
                playBtn.setVisibility(View.INVISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                videoVv.start();
                break;
            case R.id.next_btn:
                change++;
                videoPrepare();
                playBtn.setVisibility(View.INVISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                videoVv.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoVv != null) {
            videoVv.suspend();
        }
    }
}