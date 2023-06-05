package com.example.mediaplayer.Music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaplayer.R;
import static com.example.mediaplayer.Bean.Constant.songName;
import  static com.example.mediaplayer.Bean.Constant.songPic;
import  static com.example.mediaplayer.Bean.Constant.videoPic;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView exitIv;
    public static ImageView musicIv;
    public static TextView songname, progressTv, totalTv;
    public static SeekBar seekbar;
    public Button continuePlay, play, pre, next;
    public static Button pause;
    public static MusicService.MusicControl musicControl;
    //动画实现类
    public static ObjectAnimator animator;

    private boolean isUnbind = false;  // 记录服务是否被解绑
    public static Intent songIntent;
    private static int change = 0;
    private boolean transfrom = false; // 判断歌曲是否改变


    private static final String TAG = "MusicActivity";
    //这里主要是为了在handler中能使用Toast，Toast需要Context
    private static Context mContext;

    //用于链接服务
    public ServiceConnection cnn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MusicService.MusicControl) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mContext = getApplicationContext();
        initView();
    }

    private void initView() {
        exitIv = findViewById(R.id.exit_Iv);
        musicIv = findViewById(R.id.music_Iv);
        songname = findViewById(R.id.song_name);
        progressTv = findViewById(R.id.progress_Tv);
        totalTv = findViewById(R.id.total_Tv);
        seekbar = findViewById(R.id.seekbar);
        continuePlay = findViewById(R.id.btn_continue_play);
        pause = findViewById(R.id.pause_btn);
        play = findViewById(R.id.play_btn);
        pre = findViewById(R.id.pre_btn);
        next = findViewById(R.id.next_btn);

        //绑定点击事件
        exitIv.setOnClickListener(this);
        continuePlay.setOnClickListener(this);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        pre.setOnClickListener(this);
        next.setOnClickListener(this);

        songIntent = getIntent();
        int position = Integer.parseInt(songIntent.getStringExtra("position"));

        //播放从MusicFragment点击进来的歌曲
        //设置播放的歌曲名字
        songname.setText(songName[position]);
        //设置歌曲图片
        musicIv.setImageResource(songPic[position]);

        //设置动画
        animator = ObjectAnimator.ofFloat(musicIv, "rotation", 0f, 360.0f);
        animator.setDuration(8000);      //动画旋转一周为8秒
        animator.setInterpolator(new LinearInterpolator());    //动画匀速旋转
        animator.setRepeatCount(-1);           //设置重复次数，-1表示无线次重复

        //设置进度条
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //当进度结束时
                if (progress >= seekBar.getMax()) {
                    animator.pause();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //获取当前进度条位置
                int progress = seekBar.getProgress();
                musicControl.seekTo(progress);

            }
        });

        //启动服务
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, cnn, BIND_AUTO_CREATE);
    }

    private static Handler animationHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    animator.start();
                default:
                    break;
            }

        }
    };


    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //获得子线程传递过来的音乐播放数据
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");
            //发过来的就两个，一个是歌曲的最大播放时间，一个是当前的播放到歌曲的几分几秒了
            seekbar.setMax(duration);
            seekbar.setProgress(currentPosition);
            //下面计算已经播放的时间和还剩下的时间，注意歌曲总时长为毫秒

            //设置一首歌曲的总时长
            int minute = duration / 1000 / 60;
            int second = (duration / 1000) % 60;
            //设置歌曲格式
            totalTv.setText(getNormTime(minute, second));


            //设置歌曲当前播放时长
            minute = currentPosition / 1000 / 60;
            second = (currentPosition / 1000) % 60;
            progressTv.setText(getNormTime(minute, second));

            //如果歌曲播放完了，则要自动播放下一首
        }

        private String getNormTime(int minute, int second) {
            String strMinute, strSecond;
            //设置歌曲格式
            if (minute < 10) {
                strMinute = "0" + minute;
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {
                strSecond = "0" + second;
            } else {
                strSecond = second + "";
            }
            String resultTime = strMinute + ":" + strSecond;

            return resultTime;
        }

    };

    private static void playMusic() {
        int position = Integer.parseInt(songIntent.getStringExtra("position"));
        position += change;
        //如果position = -1，就播放列表中的最后一首；如果position=列表长度，则播放第一首
        if (position < 0 || position >= songName.length) {
            position = (position + songName.length) % songName.length;
        }

        Message message = Message.obtain();
        message.what = 1;
        animationHandler.sendMessage(message);
        songname.setText(songName[position]);
        musicIv.setImageResource(songPic[position]);
        musicControl.play(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_Iv:
                unbind(isUnbind);
                isUnbind = true;
                finish();
                break;
            case R.id.play_btn:
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                playMusic();
                break;
            case R.id.pause_btn:
                continuePlay.setVisibility(View.VISIBLE);
                pause.setVisibility(View.INVISIBLE);
                //暂停播放
                musicControl.pausePlay();
                //暂停播放动画
                animator.pause();
                break;
            case R.id.btn_continue_play:
                continuePlay.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                playMusic();
                break;
            case R.id.pre_btn:
                change--;
                play.setVisibility(View.INVISIBLE);
                continuePlay.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                playMusic();
                break;


            case R.id.next_btn:
                change++;
                play.setVisibility(View.INVISIBLE);
                continuePlay.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                playMusic();
                break;
        }


    }

    // 未解绑则解绑
    private void unbind(boolean isUnbind){
        if(!isUnbind){//判断服务是否被解绑
            musicControl.pausePlay();//暂停播放音乐
            unbindService(cnn);//解绑服务
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind(isUnbind);// 解绑服务
    }
}
