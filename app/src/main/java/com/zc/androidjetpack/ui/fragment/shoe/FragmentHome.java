package com.zc.androidjetpack.ui.fragment.shoe;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.adapter.ShoeRecyclerAdapter;
import com.zc.androidjetpack.base.BaseFragment;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.repository.viewModel.ShoeViewModel;

import butterknife.BindView;

public class FragmentHome extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ShoeRecyclerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        setLeftVisible(false);
        setTitleText("Shoes");
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        adapter = new ShoeRecyclerAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        ShoeViewModel shoeViewModel = getAppViewModelProvider().get(ShoeViewModel.class);
        shoeViewModel.getShoeList().observe(this, new Observer<PagedList<Shoe>>() {
            @Override
            public void onChanged(PagedList<Shoe> shoeBeans) {
                adapter.submitList(shoeBeans);
            }
        });
        // 发送给我的界面修改昵称
        getSharedViewModel().sharedListener.postValue("hello");
        adapter.setOnLikeListener(new ShoeRecyclerAdapter.OnLikeListener() {
            @Override
            public void onLike(int position, boolean isLike) {
                if (isLike) {
                    shoeViewModel.insertShoe(adapter.getCurrentList().get(position));
                } else {
                    shoeViewModel.deleteShoe(adapter.getCurrentList().get(position));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
