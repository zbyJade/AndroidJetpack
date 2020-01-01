package com.zc.androidjetpack.repository.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.zc.androidjetpack.bean.SubSortBean;
import com.zc.androidjetpack.http.HttpRequestManager;

import java.util.List;

public class SubSortDataSource extends PositionalDataSource<SubSortBean> {

    HttpRequestManager httpRequestManager = HttpRequestManager.getInstance();

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<SubSortBean> callback) {
        httpRequestManager.getSubSorts(new HttpRequestManager.HttpSuccessListCallback<SubSortBean>() {
            @Override
            public void onSuccessResultList(List<SubSortBean> valueList, int totalCount) {
                callback.onResult(valueList, 0, totalCount);
            }
        });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<SubSortBean> callback) {
        // 0-19 20-39 40-59
        httpRequestManager.getSubSorts(new HttpRequestManager.HttpSuccessListCallback<SubSortBean>() {
            @Override
            public void onSuccessResultList(List<SubSortBean> valueList, int totalCount) {
                callback.onResult(valueList);
            }
        });
    }
}
