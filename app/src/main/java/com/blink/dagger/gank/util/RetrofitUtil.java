package com.blink.dagger.gank.util;

import com.blink.dagger.gank.api.Gank;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucky on 17-1-10.
 */
public class RetrofitUtil {

    private static Gank gankService;
    private static Retrofit retrofit;

    public static Gank singletonGank (){
        if (retrofit == null) {
            synchronized (RetrofitUtil.class){
                if (retrofit == null) {
                    retrofit=new Retrofit.Builder().baseUrl("http://gank.io/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).build())
                            .build();
                }
            }
        }
        return retrofit.create(Gank.class);
    }
}
