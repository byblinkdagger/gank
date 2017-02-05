package com.blink.dagger.gank.api;

import com.blink.dagger.gank.bean.GanHuoBean;
import com.blink.dagger.gank.bean.MeiZhiBean;
import com.blink.dagger.gank.bean.VideoBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lucky on 17-1-9.
 */
public interface Gank{

    @GET ("random/data/福利/4")
    Observable<MeiZhiBean> getMeiZhi();

    //http://gank.io/api/data/Android/10/1
    @GET ("data/Android/{count}/{page}")
    Observable<GanHuoBean> getGanHuo(@Path("count") int count,
                                     @Path("page") int page);

    //http://gank.io/api/data/休息视频/10/1
    @GET ("data/休息视频/{count}/{page}")
    Observable<VideoBean> getViedo(@Path("count") int count,
                                   @Path("page") int page);
}
