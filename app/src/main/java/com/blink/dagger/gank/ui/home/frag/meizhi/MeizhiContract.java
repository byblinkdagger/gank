package com.blink.dagger.gank.ui.home.frag.meizhi;

import android.view.View;
import android.widget.ImageView;

import com.blink.dagger.gank.base.BasePresenter;
import com.blink.dagger.gank.base.BaseView;
import com.blink.dagger.gank.bean.MeiZhiBean;

import java.util.List;

/**
 * Created by lucky on 17-1-9.
 */
public class MeizhiContract {

    interface View extends BaseView {
        void startLoad();
        void reLoad();
        void setData(List<MeiZhiBean.ResultsBean> list);
        void loadError();
        void handleClick(android.view.View view);
    }

    interface Presenter extends BasePresenter {
        void getMeiZhi();
        void refresh();
        void onClick(android.view.View view);
    }

}
