package com.blink.dagger.gank.ui.home.frag.ganhuo;

import com.blink.dagger.gank.base.BasePresenter;
import com.blink.dagger.gank.base.BaseView;
import com.blink.dagger.gank.bean.GanHuoBean;

import java.util.List;

/**
 * Created by lucky on 17-1-12.
 */
public class GanHuoContract {

    interface View extends BaseView {
        void setData(List<GanHuoBean.ResultsBean> results);
        void loadError();
    }

    interface Presenter extends BasePresenter {
        void getGanhuo(int page);
        void refresh();
        void loadMore();
    }
}
