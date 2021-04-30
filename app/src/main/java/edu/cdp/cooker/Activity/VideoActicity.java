package edu.cdp.cooker.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.TimerTask;

import edu.cdp.cooker.R;


public class VideoActicity extends AppCompatActivity {
    ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_acticity);
        playVideo();
    }


    //播放请求服务器的视频
    private void playVideo() {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);


        //加载指定的视频文件
//        String path = Environment.getExternalStorageDirectory().getPath()+"/Pictures/视频.mp4";
        String path = "";

        if (getIntent().getStringExtra("requst") != null) {
            path = getIntent().getStringExtra("requst");
        }

        Uri uri = Uri.parse(path);

        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);

        //让VideoView获取焦点
        videoView.requestFocus();

        videoView.setVideoURI(uri);

        videoView.start();

        progDailog = ProgressDialog.show(this, "视频准备中...", "正在获取数据 ...", true);
        progDailog.setCancelable(true);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                progDailog.dismiss();
            }
        });

    }


}