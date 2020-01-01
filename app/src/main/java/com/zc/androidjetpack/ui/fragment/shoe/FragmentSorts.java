package com.zc.androidjetpack.ui.fragment.shoe;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.adapter.SortsRecyclerAdapter;
import com.zc.androidjetpack.adapter.SubSortsRecyclerAdapter;
import com.zc.androidjetpack.base.BaseFragment;
import com.zc.androidjetpack.base.BaseListAdapter;
import com.zc.androidjetpack.base.BasePagedListAdapter;
import com.zc.androidjetpack.bean.SortBean;
import com.zc.androidjetpack.bean.SubSortBean;
import com.zc.androidjetpack.http.HttpRequestManager;
import com.zc.androidjetpack.repository.viewModel.SubSortsViewModel;
import com.zc.androidjetpack.utils.ParamsUtil;
import com.zc.androidjetpack.widget.SimpleDividerDecoration;

import java.util.List;

import butterknife.BindView;

public class FragmentSorts extends BaseFragment {

    @BindView(R.id.sort_recyclerView)
    RecyclerView mSortRecyclerView;
    @BindView(R.id.sub_sort_recyclerView)
    RecyclerView mSubSortRecyclerView;
    private SortsRecyclerAdapter sortsRecyclerAdapter;
    private SubSortsRecyclerAdapter subSortsRecyclerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sorts;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        setLeftVisible(false);
        setTitleText("Sorts");
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        List<SortBean> sorts = HttpRequestManager.getInstance().getSorts();
        sortsRecyclerAdapter = new SortsRecyclerAdapter(sorts);
        mSortRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSortRecyclerView.addItemDecoration(new SimpleDividerDecoration(getAppContext()));
        mSortRecyclerView.setAdapter(sortsRecyclerAdapter);
        subSortsRecyclerAdapter = new SubSortsRecyclerAdapter();
        mSubSortRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mSubSortRecyclerView.setAdapter(subSortsRecyclerAdapter);
    }

    @Override
    public void initData() {
        SubSortsViewModel subSortsViewModel = getAppViewModelProvider().get(SubSortsViewModel.class);
        sortsRecyclerAdapter.setItemClickListener(new BaseListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                sortsRecyclerAdapter.setSelectedNotify(position);
                subSortsViewModel.setSubSorts(ParamsUtil.sorts[position]);
                subSortsRecyclerAdapter.notifyDataSetChanged();
            }
        });
        subSortsViewModel.getSubSorts().observe(this, new Observer<PagedList<SubSortBean>>() {
            @Override
            public void onChanged(PagedList<SubSortBean> subSortBeans) {
                subSortsRecyclerAdapter.submitList(subSortBeans);
            }
        });
        subSortsRecyclerAdapter.setItemClickListener(new BasePagedListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showLongToast(subSortsRecyclerAdapter.getCurrentList().get(position).getSubSortName());
            }
        });
    }
}
