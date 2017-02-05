package com.blink.dagger.gank.ui.home.frag.meizhi;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blink.dagger.gank.R;
import com.blink.dagger.gank.bean.MeiZhiBean;
import com.blink.dagger.gank.ui.meicontent.MeizhiContentActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeizhiFragment extends Fragment implements MeizhiContract.View,View.OnClickListener {

    @InjectView(R.id.iv_firstcard)
    ImageView ivFirstcard;
    @InjectView(R.id.iv_secondcard)
    ImageView ivSecondcard;
    @InjectView(R.id.fresh_fab)
    FloatingActionButton freshFab;
    @InjectView(R.id.iv_thirdcard)
    ImageView ivThirdcard;
    @InjectView(R.id.iv_fourthcard)
    ImageView ivFourthcard;

    ImageView[] imageViews;
    MeizhiPresenter meizhiPresenter;
    private List<MeiZhiBean.ResultsBean> list;

    public static MeizhiFragment NewInstance() {
        return new MeizhiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi, container, false);
        ButterKnife.inject(this, view);
        list = new ArrayList<MeiZhiBean.ResultsBean>();
        meizhiPresenter = new MeizhiPresenter(this, getContext());
        meizhiPresenter.getMeiZhi();
        imageViews = new ImageView[]{ivFirstcard, ivSecondcard, ivThirdcard, ivFourthcard};
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setOnClickListener(this);
        }
        freshFab.setOnClickListener(this);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void startLoad() {
        for (int i = 0; i < imageViews.length; i++) {
            Picasso.with(getContext()).load(list.get(i).getUrl()).into(imageViews[i]);
        }
    }

    @Override
    public void reLoad() {
        for (int i = 0; i < imageViews.length; i++) {
            Picasso.with(getContext()).load(list.get(i).getUrl()).into(imageViews[i]);
        }
    }

    @Override
    public void setData(List<MeiZhiBean.ResultsBean> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public void loadError() {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(R.drawable.gakki);
        }
    }

    @Override
    public void onClick(View v) {
        meizhiPresenter.onClick(v);
    }

    @Override
    public void handleClick(View view) {
        Intent meizhiIntent = new Intent(getContext(), MeizhiContentActivity.class);
        switch (view.getId()) {

            case R.id.fresh_fab:
                meizhiPresenter.refresh();
                break;

            case R.id.iv_firstcard:
                meizhiIntent.putExtra("picURL",list.get(0).getUrl());
                startActivity(meizhiIntent);
                break;

            case R.id.iv_secondcard:
                meizhiIntent.putExtra("picURL",list.get(1).getUrl());
                startActivity(meizhiIntent);
                break;

            case R.id.iv_thirdcard:
                meizhiIntent.putExtra("picURL",list.get(2).getUrl());
                startActivity(meizhiIntent);
                break;

            case R.id.iv_fourthcard:
                meizhiIntent.putExtra("picURL",list.get(3).getUrl());
                startActivity(meizhiIntent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
