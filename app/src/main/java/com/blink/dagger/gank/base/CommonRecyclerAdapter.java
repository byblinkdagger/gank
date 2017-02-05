package com.blink.dagger.gank.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lucky on 16-8-1.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerAdapter.ViewHolder>{
    private Context context;
    private int layoutResId;
    private List<T> data;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;


    public CommonRecyclerAdapter(Context context, int layoutResId,List<T> data) {
        this.context=context;
        this.layoutResId=layoutResId;
        this.data=data;

    }
    public CommonRecyclerAdapter(Context context, int layoutResId) {
        this(context,layoutResId,null);

    }
    //用于回调子view的点击
    public interface OnRecyclerViewItemClickListener {
        public void onItemClick(View view, int position);
    }
    //设置listener
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layoutResId,parent,false);
        final ViewHolder holder=new ViewHolder(context,view);
        if (onRecyclerViewItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, data.get(position));
    }
    //在具体的adapter中实现此抽象方法即可
    protected abstract void convert(ViewHolder holder, T t) ;

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> views;
        private final Context context;
        private View convertView;
        //将holder中的子view存储至SparseArray中
        protected ViewHolder(Context context,View itemView) {
            super(itemView);
            this.context=context;
            this.convertView=itemView;
            this.views=new SparseArray<View>();
        }
        //每次操作子View前，执行此方法，若该子view已在SparseArray中直接返回，如不存在则实例化它并将其存放至SparseArray
        protected <T extends View> T retrieveView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }
        //处理子View (根据具体情况还可增加setChecked,setTag,setvisible等方法)
        public ViewHolder setText(int viewId, String value) {
            TextView view = retrieveView(viewId);
            view.setText(value);
            return this;
        }

        public ViewHolder setCharSequence(int viewId, CharSequence value) {
            TextView view = retrieveView(viewId);
            view.setText(value);
            return this;
        }

        public ViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = retrieveView(viewId);
            view.setImageResource(imageResId);
            return this;
        }
        //add for loading image
        public ViewHolder setImageURI(int viewId, String imageURI) {
            ImageView view = retrieveView(viewId);
            Picasso.with(context).load(imageURI).into(view);
            return this;
        }

        public ViewHolder setBackgroundColor(int viewId, int color) {
            View view = retrieveView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

    }
}