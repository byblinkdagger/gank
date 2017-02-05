package com.blink.dagger.gank.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.ui.home.HomeActivity;
import com.blink.dagger.gank.util.DensityUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

    @InjectView(R.id.iv_welcome_logo1)
    ImageView ivWelcomeLogo1;
    @InjectView(R.id.iv_welcome_logo2)
    ImageView ivWelcomeLogo2;
    @InjectView(R.id.tv_welcome_label)
    TextView tvWelcomeLabel;
    @InjectView(R.id.tv_welcome_label2)
    TextView tvWelcomeLabel2;
    @InjectView(R.id.iv_next1)
    ImageView ivNext1;
    @InjectView(R.id.iv_next2)
    ImageView ivNext2;
    @InjectView(R.id.iv_next3)
    ImageView ivNext3;

    private WelcomePresenter welcomePresenter;
    private final int START_UP_DELAY = 300;
    private final int ANIM_DURATION = 1000;
    private final int ANIM_DELAY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        welcomePresenter = new WelcomePresenter(this);
        welcomePresenter.start();
    }

    @Override
    public void anim() {
        ViewCompat.animate(ivWelcomeLogo1)
                .alpha(0)
                .translationY(DensityUtil.dip2px(this, 40))
                .setStartDelay(ANIM_DURATION + START_UP_DELAY)
                .setDuration(ANIM_DURATION)
                .start();

        ViewCompat.animate(ivWelcomeLogo2)
                .alpha(0)
                .translationY(-DensityUtil.dip2px(this, 40))
                .setStartDelay(ANIM_DURATION)
                .setDuration(ANIM_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        ViewCompat.animate(tvWelcomeLabel)
                .alpha(1)
                .translationY(DensityUtil.dip2px(this, 40))
                .setStartDelay(ANIM_DURATION + START_UP_DELAY)
                .setDuration(ANIM_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        ViewCompat.animate(tvWelcomeLabel2)
                .alpha(1)
                .translationY(DensityUtil.dip2px(this, 40))
                .setStartDelay(ANIM_DURATION + START_UP_DELAY + ANIM_DELAY)
                .setDuration(ANIM_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .start();

        ViewCompat.animate(ivNext1)
                .alpha(1)
                .setStartDelay(ANIM_DURATION + START_UP_DELAY + ANIM_DELAY * 2)
                .setDuration(ANIM_DURATION)
                .start();

        ViewCompat.animate(ivNext2)
                .alpha(1)
                .setStartDelay(ANIM_DURATION + START_UP_DELAY + ANIM_DELAY * 3)
                .setDuration(ANIM_DURATION)
                .start();

        ViewCompat.animate(ivNext3)
                .alpha(1)
                .setStartDelay(ANIM_DURATION + START_UP_DELAY + ANIM_DELAY * 4)
                .setDuration(ANIM_DURATION)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                        WelcomeActivity.this.overridePendingTransition(R.anim.ac_still, R.anim.ac_center_out);
                        WelcomeActivity.this.finish();
                    }
                })
                .start();
    }
}
