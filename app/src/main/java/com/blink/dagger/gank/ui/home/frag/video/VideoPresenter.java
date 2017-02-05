package com.blink.dagger.gank.ui.home.frag.video;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blink.dagger.gank.bean.GanHuoBean;
import com.blink.dagger.gank.bean.VideoBean;
import com.blink.dagger.gank.util.RetrofitUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by blink_dagger on 17-1-20.
 */
public class VideoPresenter implements VideoContract.Presenter{

    private Context mContext;
    private VideoContract.View mView;

    public VideoPresenter(Context mContext, VideoContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void getVideo(int page) {
        Subscriber<VideoBean> subscriber = new Subscriber<VideoBean>() {
            @Override
            public void onCompleted() {
                //Toast.makeText(mContext, "getVideo Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                mView.loadError();
            }

            @Override
            public void onNext(VideoBean videoBean) {
                if (videoBean.getResults().size() != 0) {
                    Log.e("lxg","datas != null");
                    mView.setData(videoBean.getResults());
                }
            }
        };
        RetrofitUtil.singletonGank().getViedo(20,page).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void refresh() {
        getVideo(1);
    }

    @Override
    public void loadMore() {
        getVideo(2);
    }
}
