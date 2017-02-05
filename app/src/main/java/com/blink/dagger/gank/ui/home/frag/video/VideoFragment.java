package com.blink.dagger.gank.ui.home.frag.video;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.base.CommonRecyclerAdapter;
import com.blink.dagger.gank.bean.VideoBean;
import com.blink.dagger.gank.ui.video.VideoContentActivity;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class VideoFragment extends Fragment implements VideoContract.View {


    @InjectView(R.id.iv_video_header)
    ImageView ivVideoHeader;
    @InjectView(R.id.video_recycle_view)
    RecyclerView videoRecycleView;
    @InjectView(R.id.video_refresh)
    MaterialRefreshLayout videoRefresh;

    private List<VideoBean.ResultsBean> mList;
    private VideoPresenter videoPresenter;
    private CommonRecyclerAdapter<VideoBean.ResultsBean> adapter;
    private Context mContext;
    private String mVideoPreviewUrl;

    public static VideoFragment NewInstance() {
        return new VideoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.inject(this, view);
        initAll();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoRefresh.setIsOverLay(false);
        videoRefresh.setWaveShow(false);
        videoRefresh.setLoadMore(true);
        videoRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                videoPresenter.refresh();
                videoRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (videoRefresh != null) {
                            videoRefresh.finishRefresh();
                        }
                    }
                },2000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                videoPresenter.loadMore();
                videoRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (videoRefresh != null) {
                            videoRefresh.finishRefreshLoadMore();
                        }
                    }
                },2000);
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }
        });
    }

    private void initAll() {
        mContext = getContext();
        mList = new ArrayList<VideoBean.ResultsBean>();
        videoRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        videoRecycleView.setItemAnimator(new DefaultItemAnimator());
        if (mList!=null) {
            adapter = new CommonRecyclerAdapter<VideoBean.ResultsBean>(mContext, R.layout.item_video_pre,mList) {
                @Override
                protected void convert(ViewHolder holder, VideoBean.ResultsBean resultsBean) {
                    holder.setText(R.id.tv_video_title,resultsBean.getDesc());
                    //2017-01-02T22:04:07.793Z
                    String text[]=resultsBean.getCreatedAt().split("T");
                    holder.setText(R.id.tv_video_time,text[0]);

                    //try unsuccessfully,do nothing at all
                    String date[]=resultsBean.getCreatedAt().split("-");
                    String day= ""+resultsBean.getCreatedAt().charAt(8)+resultsBean.getCreatedAt().charAt(9);
                    Log.d("luck","date[0] := "+date[0]);
                    Log.d("luck","date[1] := "+date[1]);
                    Log.d("luck","day := "+day);
                    String url = getPreImageUrl(date[0],date[1],day);
                    //Log.d("luck","url_pre := "+url);
                    if (url != null) {
                        Log.d("luck","url := "+url);
                        holder.setImageURI(R.id.iv_video_pre, url);
                    }
                }
            };
            adapter.setOnRecyclerViewItemClickListener(new CommonRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent webIntent = new Intent(getContext(), VideoContentActivity.class);
                    webIntent.putExtra("url",mList.get(position).getUrl());
                    startActivity(webIntent);

                }
            });
            videoRecycleView.setAdapter(adapter);
        }
        videoPresenter = new VideoPresenter(mContext,this);
        videoPresenter.getVideo(1);
    }

    @Override
    public void setData(List<VideoBean.ResultsBean> results) {
        mList.clear();
        mList.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadError() {
        Toast.makeText(mContext,"出错啦~对不起哦",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //do nothing
    private String getPreImageUrl(String mYear,String mMonth,String mDay) {
        String url = "http://gank.io/" + String.format("%s/%s/%s", mYear, mMonth, mDay);
        Log.d("luck","url := "+url);
        Request request = new Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("luck","11111111111");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                mVideoPreviewUrl = getVideoPreviewImageUrl(body);
                Log.d("luck","2222  body:= "+body);
            }
        });
        return mVideoPreviewUrl;
    }

    //do nothing
    private String getVideoPreviewImageUrl(String resp) {
        int s0 = resp.indexOf("<h1>休息视频</h1>");
        if (s0 == -1) return null;
        int s1 = resp.indexOf("<img", s0);
        if (s1 == -1) return null;
        int s2 = resp.indexOf("http:", s1);
        if (s2 == -1) return null;
        int e2 = resp.indexOf(".jpg", s2) + ".jpg".length();
        if (e2 == -1) return null;
        try {
            return resp.substring(s2, e2);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
