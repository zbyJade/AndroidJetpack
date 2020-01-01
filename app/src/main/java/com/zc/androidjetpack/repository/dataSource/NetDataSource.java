package com.zc.androidjetpack.repository.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.zc.androidjetpack.bean.NetBean;
import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.http.HttpRequestManager;

import java.util.List;

public class NetDataSource extends PositionalDataSource<NetBean.DataBean> {

    HttpRequestManager httpRequestManager = HttpRequestManager.getInstance();

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<NetBean.DataBean> callback) {
        httpRequestManager.getNetList(1, params.pageSize, new HttpRequestManager.HttpSuccessListCallback<NetBean.DataBean>() {
            @Override
            public void onSuccessResultList(List<NetBean.DataBean> valueList, int totalCount) {
                callback.onResult(valueList, 0, totalCount);
            }
        });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<NetBean.DataBean> callback) {
        // 0-19 20-39 40-59
        httpRequestManager.getNetList(1 + params.startPosition / params.loadSize, params.loadSize, new HttpRequestManager.HttpSuccessListCallback<NetBean.DataBean>() {
            @Override
            public void onSuccessResultList(List<NetBean.DataBean> valueList, int totalCount) {
                callback.onResult(valueList);
            }
        });
    }
}
