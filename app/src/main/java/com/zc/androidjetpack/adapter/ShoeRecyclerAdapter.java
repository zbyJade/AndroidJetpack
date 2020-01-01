package com.zc.androidjetpack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;

import com.zc.androidjetpack.base.BasePagedListAdapter;
import com.kunminx.architecture.ui.viewholder.BaseViewHolder;
import com.zc.androidjetpack.App;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.repository.viewModel.ShoeViewModel;

public class ShoeRecyclerAdapter extends BasePagedListAdapter<Shoe> {

    public ShoeRecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnLikeListener onLikeListener;

    public interface OnLikeListener {
        void onLike(int position, boolean isLike);
    }

    public void setOnLikeListener(OnLikeListener onLikeListener) {
        this.onLikeListener = onLikeListener;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoe, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        TextView mTvShoeName = holder.getView(R.id.tv_shoeName);
        ImageView mIvLike = holder.getView(R.id.iv_like);
        Shoe shoeBean = getItem(position);
        mIvLike.setImageResource(shoeBean.isLike() ? R.drawable.svg_liked : R.drawable.svg_like);
        if (shoeBean != null) {
            mTvShoeName.setText(shoeBean.getShoeName());
        }
        mIvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoeBean.setLike(!shoeBean.isLike());
                if (onLikeListener != null) {
                    onLikeListener.onLike(position, shoeBean.isLike());
                }
            }
        });
    }

    private static DiffUtil.ItemCallback<Shoe> DIFF_CALLBACK = new DiffUtil.ItemCallback<Shoe>() {

        @Override
        public boolean areItemsTheSame(@NonNull Shoe oldItem, @NonNull Shoe newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shoe oldItem, @NonNull Shoe newItem) {
            return oldItem.equals(newItem);
        }
    };
}
