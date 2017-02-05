package com.blink.dagger.gank.ui.home.frag.ganhuo;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.blink.dagger.gank.bean.GanHuoBean;
import com.blink.dagger.gank.bean.MeiZhiBean;
import com.blink.dagger.gank.util.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lucky on 17-1-12.
 */
public class GanHuoPresenter implements GanHuoContract.Presenter {

    private Context mContext;
    private GanHuoContract.View view;

    public GanHuoPresenter(GanHuoContract.View view, Context context) {
        this.view = view;
        mContext = context;
    }

    @Override
    public void getGanhuo(int page) {
        Subscriber<GanHuoBean> subscriber = new Subscriber<GanHuoBean>() {
            @Override
            public void onCompleted() {
                //Toast.makeText(mContext, "getGanHuo Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                view.loadError();
            }

            @Override
            public void onNext(GanHuoBean ganHuoBean) {
                if (ganHuoBean.getResults().size() != 0) {
                    Log.e("lxg","datas != null");
                    view.setData(ganHuoBean.getResults());
                }
            }
        };
        RetrofitUtil.singletonGank().getGanHuo(20,page).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void refresh() {
        getGanhuo(1);
    }

    @Override
    public void loadMore() {
        getGanhuo(2);
    }
}
