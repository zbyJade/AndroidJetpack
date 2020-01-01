package com.zc.androidjetpack.repository.factory;

import androidx.paging.DataSource;

import com.kunminx.architecture.bridge.callback.UnPeekLiveData;
import com.zc.androidjetpack.bean.SubSortBean;
import com.zc.androidjetpack.repository.dataSource.SubSortDataSource;

public class SubSortsFactory extends DataSource.Factory<Integer, SubSortBean> {

    private UnPeekLiveData<SubSortDataSource> mSourceLiveData = new UnPeekLiveData<>();

    @Override
    public DataSource<Integer, SubSortBean> create() {
        SubSortDataSource subSortDataSource = new SubSortDataSource();
        mSourceLiveData.postValue(subSortDataSource);
        return subSortDataSource;
    }
}
