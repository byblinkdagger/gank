package com.blink.dagger.gank.ui.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.widget.LoveVideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoContentActivity extends AppCompatActivity {

    @InjectView(R.id.video_player)
    LoveVideoView videoPlayer;

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_content);
        mUrl = getIntent().getStringExtra("url");
        ButterKnife.inject(this);
        videoPlayer.loadUrl(mUrl);
    }
}
