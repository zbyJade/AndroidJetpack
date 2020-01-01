package com.zc.androidjetpack.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kunminx.architecture.ui.viewholder.BaseViewHolder;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseListAdapter;
import com.zc.androidjetpack.bean.SortBean;

import java.util.List;

public class SortsRecyclerAdapter extends BaseListAdapter<SortBean> {

    private List<SortBean> sortBeans;

    public SortsRecyclerAdapter(List<SortBean> sortBeans) {
        this.sortBeans = sortBeans;
    }

    public void setSelectedNotify(int position) {
        for (int i = 0; i < sortBeans.size(); i++) {
            sortBeans.get(i).setSelected(i == position);
        }
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sort, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        TextView mTvSortName = holder.getView(R.id.tv_sortName);
        View mViewLabel = holder.getView(R.id.view_label);
        mTvSortName.setText(sortBeans.get(position).getSortName());
        boolean selected = sortBeans.get(position).isSelected();
        if (selected) {
            mViewLabel.setVisibility(View.VISIBLE);
            mTvSortName.setBackgroundResource(R.color.norm_white);
            mTvSortName.setTextColor(mTvSortName.getContext().getResources().getColor(R.color.shoe_theme_color, null));
        } else {
            mViewLabel.setVisibility(View.GONE);
            mTvSortName.setBackgroundResource(R.color.norm_grey);
            mTvSortName.setTextColor(mTvSortName.getContext().getResources().getColor(R.color.color_6, null));
        }
    }

    @Override
    public int getItemCount() {
        return sortBeans.size();
    }
}
