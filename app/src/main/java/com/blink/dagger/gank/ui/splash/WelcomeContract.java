package com.blink.dagger.gank.ui.splash;

import com.blink.dagger.gank.base.BasePresenter;
import com.blink.dagger.gank.base.BaseView;

/**
 * Created by blink_dagger on 17-2-5.
 */
public class WelcomeContract {
    interface View extends BaseView {
        void anim();
    }

    interface Presenter extends BasePresenter {
        void start();
    }
}
