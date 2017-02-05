package com.blink.dagger.gank.ui.home.frag.ganhuo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.base.CommonRecyclerAdapter;
import com.blink.dagger.gank.bean.GanHuoBean;
import com.blink.dagger.gank.ui.gancontent.GanhuoDetailActivity;
import com.blink.dagger.gank.util.StringStyles;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GanHuoFragment extends Fragment implements GanHuoContract.View {

    @InjectView(R.id.iv_ganhuo_header)
    ImageView ivGanhuoHeader;
    //@InjectView(R.id.toolbar_ganhuo_detail)
    //Toolbar toolbarGanhuoDetail;
    @InjectView(R.id.ganhuo_recycle_view)
    RecyclerView ganhuoRecycleView;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private List<GanHuoBean.ResultsBean> mList;
    private GanHuoPresenter ganHuoPresenter;
    private CommonRecyclerAdapter<GanHuoBean.ResultsBean> adapter;
    private Context mContext;

    public static GanHuoFragment NewInstance() {
        return new GanHuoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gan_huo, container, false);
        ButterKnife.inject(this, view);
        initAll();
        return view;
    }

    private void initAll() {
        mContext = getContext();
        mList = new ArrayList<GanHuoBean.ResultsBean>();
        ganhuoRecycleView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        ganhuoRecycleView.setItemAnimator(new DefaultItemAnimator());
        if (mList!=null) {
            adapter = new CommonRecyclerAdapter<GanHuoBean.ResultsBean>(mContext, R.layout.item_ganhuo_pre,mList) {
                @Override
                protected void convert(ViewHolder holder, GanHuoBean.ResultsBean resultsBean) {
                    holder.setText(R.id.tv_title,resultsBean.getType());
                    SpannableStringBuilder builder = new SpannableStringBuilder(resultsBean.getDesc()).append(
                            StringStyles.format(mContext, " (via. " +
                                    resultsBean.getWho() +
                                    ")", R.style.ViaTextAppearance));
                    CharSequence descTxt = builder.subSequence(0, builder.length());
                    holder.setCharSequence(R.id.tv_description, descTxt);
                }
            };
            adapter.setOnRecyclerViewItemClickListener(new CommonRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent webIntent = new Intent(getContext(), GanhuoDetailActivity.class);
                    webIntent.putExtra("title",mList.get(position).getDesc());
                    webIntent.putExtra("url",mList.get(position).getUrl());
                    startActivity(webIntent);

                }
            });
            ganhuoRecycleView.setAdapter(adapter);
        }
        ganHuoPresenter = new GanHuoPresenter(this,mContext);
        ganHuoPresenter.getGanhuo(1);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh.setIsOverLay(false);
        refresh.setWaveShow(false);
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                ganHuoPresenter.refresh();
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refresh != null) {
                            refresh.finishRefresh();
                        }
                    }
                },2000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                ganHuoPresenter.loadMore();
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refresh != null) {
                            refresh.finishRefreshLoadMore();
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

    @Override
    public void setData(List<GanHuoBean.ResultsBean> results) {
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
}
