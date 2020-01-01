package com.zc.androidjetpack.repository.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zc.androidjetpack.bean.SubSortBean;
import com.zc.androidjetpack.repository.factory.SubSortsFactory;
import com.zc.androidjetpack.utils.ParamsUtil;

public class SubSortsViewModel extends AndroidViewModel {

    private final LiveData<PagedList<SubSortBean>> subSorts;
    private DataSource<Integer, SubSortBean> subSortBeanDataSource;

    public SubSortsViewModel(@NonNull Application application) {
        super(application);
        SubSortsFactory subSortsFactory = new SubSortsFactory();
        subSortBeanDataSource = subSortsFactory.create();
        subSorts = new LivePagedListBuilder<>(subSortsFactory, ParamsUtil.SIZE).build();
    }

    public void invalidateDataSource() {
        subSortBeanDataSource.invalidate();
    }

    public LiveData<PagedList<SubSortBean>> getSubSorts() {
        return subSorts;
    }

    public void setSubSorts(String subSort) {
        for (int i = 0; i < subSorts.getValue().size(); i++) {
            subSorts.getValue().get(i).setSubSortName(subSort + "-" + i);
        }
    }
}
