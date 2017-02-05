package com.blink.dagger.gank.ui.home.frag.video;

import com.blink.dagger.gank.base.BasePresenter;
import com.blink.dagger.gank.base.BaseView;
import com.blink.dagger.gank.bean.GanHuoBean;
import com.blink.dagger.gank.bean.VideoBean;

import java.util.List;

/**
 * Created by blink_dagger on 17-1-20.
 */
public class VideoContract {

    interface View extends BaseView {
        void setData(List<VideoBean.ResultsBean> results);
        void loadError();
    }

    interface Presenter extends BasePresenter {
        void getVideo(int page);
        void refresh();
        void loadMore();
    }
}
