package com.blink.dagger.gank.ui.home.frag.meizhi;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blink.dagger.gank.bean.MeiZhiBean;
import com.blink.dagger.gank.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lucky on 17-1-9.
 */
public class MeizhiPresenter implements MeizhiContract.Presenter {

    private MeizhiContract.View view;
    private Context mContext;

    public MeizhiPresenter(MeizhiContract.View view,Context context) {
        this.view = view;
        mContext = context;
    }

    public void getMeiZhi() {
        Subscriber<MeiZhiBean> subscriber = new Subscriber<MeiZhiBean>() {
            @Override
            public void onCompleted() {
                //Toast.makeText(mContext, "getMeiZhi Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                view.loadError();
            }

            @Override
            public void onNext(MeiZhiBean meiZhiBean) {
                if (meiZhiBean.getResults().size() != 0) {
                    Log.e("lxg","datas != null");
                    view.setData(meiZhiBean.getResults());
                    view.startLoad();
                }
            }
        };
        RetrofitUtil.singletonGank().getMeiZhi().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void refresh() {
        getMeiZhi();
        view.reLoad();
    }

    @Override
    public void onClick(View view) {
        this.view.handleClick(view);
    }
}
