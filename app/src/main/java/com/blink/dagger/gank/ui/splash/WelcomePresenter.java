package com.blink.dagger.gank.ui.splash;

import android.content.Context;

/**
 * Created by blink_dagger on 17-2-5.
 */
public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View mView;
    public WelcomePresenter(WelcomeContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.anim();
    }
}
