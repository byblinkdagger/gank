package com.blink.dagger.gank.ui.meicontent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.util.BitmapUtil;
import com.blink.dagger.gank.util.ColorMatrixUtil;
import com.blink.dagger.gank.util.DensityUtil;
import com.blink.dagger.gank.widget.PinchImageView;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MeizhiContentActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.iv_content)
    PinchImageView ivContent;
    @InjectView(R.id.root_view)
    RelativeLayout rootView;
    @InjectView(R.id.fab_cut_pic)
    FloatingActionButton fabCutPic;

    private Intent meizhiIntent;
    private String cacheDir;
    private String picUrl;

    private ViewStub viewStub;
    boolean canExpanded = true;

    View thumbView;
    ImageView view_black;
    ImageView view_old;
    ImageView view_polaroid;
    ImageView view_green;
    ImageView view_yellow;
    ImageView view_origin;

     Bitmap originBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_content);
        ButterKnife.inject(this);
        meizhiIntent = getIntent();
        cacheDir = getExternalCacheDir().getAbsolutePath();
        viewStub = (ViewStub) findViewById(R.id.view_stub);
        picUrl = meizhiIntent.getStringExtra("picURL");
        Picasso.with(this).load(picUrl).into(ivContent);
        fabCutPic.setOnClickListener(this);
        originBitmap = BitmapUtil.drawableToBitmap(ivContent.getDrawable());
        savaPic();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_cut_pic:
                if (canExpanded) {
                    viewStub.setVisibility(View.VISIBLE);
                    canExpanded = false;
                    initImageFilter(originBitmap);
                    registerClickListener();
                } else {
                    viewStub.setVisibility(View.INVISIBLE);
                    canExpanded = true;
                }
                break;

            case R.id.iv_black:
                ivContent.setImageBitmap(ColorMatrixUtil.changeToGray(originBitmap));
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;

            case R.id.iv_old:
                ivContent.setImageBitmap(ColorMatrixUtil.changeToOld(originBitmap));
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;

            case R.id.iv_polaroid:
                ivContent.setImageBitmap(ColorMatrixUtil.changeToPolaroid(originBitmap));
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;

            case R.id.iv_green:
                ivContent.setImageBitmap(ColorMatrixUtil.changeToGreen(originBitmap));
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;

            case R.id.iv_yellow:
                ivContent.setImageBitmap(ColorMatrixUtil.changeToYellow(originBitmap));
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;

            case R.id.iv_origin:
                viewStub.setVisibility(View.INVISIBLE);
                canExpanded = true;
                break;
        }
    }

    private void savaPic() {
        ivContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (meizhiIntent != null) {
                    String uri = meizhiIntent.getStringExtra("picURL");
                    Bitmap bitmap = BitmapUtil.drawableToBitmap(ivContent.getDrawable());
                    boolean isSuccess = BitmapUtil.saveBitmap(bitmap, cacheDir, uri.substring(uri.lastIndexOf("/") + 1, uri.length()), MeizhiContentActivity.this, true);
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

    /*@OnClick(R.id.fab_cut_pic)
    public void onClick() {
        if (canExpanded) {
            viewStub.setVisibility(View.VISIBLE);
            canExpanded = false;
            initImageFilter();
        } else {
            viewStub.setVisibility(View.INVISIBLE);
            canExpanded = true;
        }
    }*/


    //滤镜preview
    private void initImageFilter(Bitmap bitmap) {

        if (thumbView == null) {
            thumbView = findViewById(R.id.vs_thumb);
        }
        if (view_black == null) {
            view_black = (ImageView) thumbView.findViewById(R.id.iv_black);
        }

        if (view_old == null) {
            view_old = (ImageView) thumbView.findViewById(R.id.iv_old);
        }

        if (view_polaroid == null) {
            view_polaroid = (ImageView) thumbView.findViewById(R.id.iv_polaroid);
        }

        if (view_green == null) {
            view_green = (ImageView) thumbView.findViewById(R.id.iv_green);
        }

        if (view_yellow == null) {
            view_yellow = (ImageView) thumbView.findViewById(R.id.iv_yellow);
        }

        if (view_origin == null) {
            view_origin = (ImageView) thumbView.findViewById(R.id.iv_origin);
        }

        //设置各滤镜效果缩略图
        view_black.setImageBitmap(ColorMatrixUtil.changeToGray(bitmap));
        view_old.setImageBitmap(ColorMatrixUtil.changeToOld(bitmap));
        view_polaroid.setImageBitmap(ColorMatrixUtil.changeToPolaroid(bitmap));
        view_green.setImageBitmap(ColorMatrixUtil.changeToGreen(bitmap));
        view_yellow.setImageBitmap(ColorMatrixUtil.changeToYellow(bitmap));
        view_origin.setImageBitmap(bitmap);
    }

    private void registerClickListener() {
        view_black.setOnClickListener(this);
        view_old.setOnClickListener(this);
        view_polaroid.setOnClickListener(this);
        view_green.setOnClickListener(this);
        view_yellow.setOnClickListener(this);
        view_origin.setOnClickListener(this);
    }
}
