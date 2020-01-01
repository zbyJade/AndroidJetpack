package com.zc.androidjetpack.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.viewholder.BaseViewHolder;

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final BaseViewHolder viewHolder = OnCreateViewHolder(parent, viewType);
        //itemView 的点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.getAdapterPosition());
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.itemView.setId(position);
        OnBindViewHolder(holder, position);
    }

    abstract public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType);

    abstract public void OnBindViewHolder(BaseViewHolder holder, final int position);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

}
