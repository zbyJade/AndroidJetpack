package com.zc.androidjetpack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.kunminx.architecture.ui.viewholder.BaseViewHolder;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BasePagedListAdapter;
import com.zc.androidjetpack.bean.SubSortBean;

public class SubSortsRecyclerAdapter extends BasePagedListAdapter<SubSortBean> {

    public SubSortsRecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_sorts, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        TextView tvShoeName = holder.getView(R.id.tv_shoe_name);
        SubSortBean item = getItem(position);
        tvShoeName.setText(item.getSubSortName());
    }

    private static DiffUtil.ItemCallback<SubSortBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<SubSortBean>() {

        @Override
        public boolean areItemsTheSame(@NonNull SubSortBean oldItem, @NonNull SubSortBean newItem) {
            return oldItem.getSubSortId().equals(newItem.getSubSortId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubSortBean oldItem, @NonNull SubSortBean newItem) {
            return oldItem.equals(newItem);
        }
    };
}
