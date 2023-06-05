package com.example.mediaplayer.Music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {

    public static MediaPlayer player;
    private Timer timer;

    public MusicService() {
    }

    private void addTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (player == null) return;
                        //获取音乐总播放时间，和当前播放时间
                        int duration = player.getDuration();
                        int currentPosition = player.getCurrentPosition();
                        //把数据打包
                        Bundle bundle = new Bundle();
                        bundle.putInt("duration", duration);
                        bundle.putInt("currentPosition", currentPosition);
                        Message msg = MusicActivity.handler.obtainMessage();
                        msg.setData(bundle);
                        //将消息发送到主线程也就是MusicActivity当中
                        MusicActivity.handler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            timer.schedule(task, 0, 500);
        }

    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }



    @Override
    public IBinder onBind(Intent intent) {
        return new MusicControl();
    }

    //把需要在MusicActivity当中用到的方法，写道这个类当中去
    class MusicControl extends Binder {
        public void play(int i) {
            //这个地质写服务器的地质
            Uri uri = Uri.parse("http://192.168.0.101:8080/music/music" + i + ".mp3");
            try {
                player.reset();
                player = MediaPlayer.create(getApplicationContext(),uri);
                player.start();
                //这一步是为了将播放事件发送到MusciAcitivity当中去
                addTimer();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void pausePlay(){
            player.pause();              //暂停播放音乐
        }
        public void continuePlay(){
            player.start();              //继续播放音乐
        }
        public void seekTo(int progress){
            player.seekTo(progress);     //设置音乐的播放位置
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player == null) {
            return;
        }
        player.stop();
        player.release();
        player = null;
    }
}