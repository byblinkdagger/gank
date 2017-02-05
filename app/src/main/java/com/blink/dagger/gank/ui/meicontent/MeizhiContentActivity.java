package com.blink.dagger.gank.ui.meicontent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.util.BitmapUtil;
import com.blink.dagger.gank.widget.PinchImageView;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MeizhiContentActivity extends AppCompatActivity {

    @InjectView(R.id.iv_content)
    PinchImageView ivContent;
    @InjectView(R.id.root_view)
    RelativeLayout rootView;

    private Intent meizhiIntent;
    private String cacheDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_content);
        ButterKnife.inject(this);
        meizhiIntent = getIntent();
        cacheDir = getExternalCacheDir().getAbsolutePath();
        Log.e("lxg","cacheDir :="+cacheDir);
        Picasso.with(this).load(meizhiIntent.getStringExtra("picURL")).into(ivContent);
        ivContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (meizhiIntent != null) {
                    String uri = meizhiIntent.getStringExtra("picURL");
                    Bitmap bitmap = BitmapUtil.drawableToBitmap(ivContent.getDrawable());
                    boolean isSuccess = BitmapUtil.saveBitmap(bitmap, cacheDir, uri.substring(uri.lastIndexOf("/") + 1, uri.length()),MeizhiContentActivity.this, true);
                    if (isSuccess) {
                        Snackbar.make(rootView, "下载好了呢~", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(rootView, "下载出错了哦~", Snackbar.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });
    }
}
