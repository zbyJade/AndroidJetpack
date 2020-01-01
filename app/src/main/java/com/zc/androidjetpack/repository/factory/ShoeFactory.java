package com.zc.androidjetpack.repository.factory;

import androidx.paging.DataSource;

import com.kunminx.architecture.bridge.callback.UnPeekLiveData;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.repository.dataSource.ShoeDataSource;

public class ShoeFactory extends DataSource.Factory<Integer, Shoe> {

    private UnPeekLiveData<ShoeDataSource> mSourceLiveData = new UnPeekLiveData<>();

    @Override
    public DataSource<Integer, Shoe> create() {
        ShoeDataSource shoeDataSource = new ShoeDataSource();
        mSourceLiveData.postValue(shoeDataSource);
        return shoeDataSource;
    }
}
