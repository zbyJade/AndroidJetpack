package com.zc.androidjetpack.repository.dataSource;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.http.HttpRequestManager;

import java.util.List;

public class ShoeDataSource extends PositionalDataSource<Shoe> {

    HttpRequestManager httpRequestManager = HttpRequestManager.getInstance();

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Shoe> callback) {
        httpRequestManager.getTestList(new HttpRequestManager.HttpSuccessListCallback<Shoe>() {
            @Override
            public void onSuccessResultList(List<Shoe> valueList, int totalCount) {
                for (int i = 0; i < valueList.size(); i++) {
                    int i1 = params.requestedStartPosition + i;
                    valueList.get(i).setShoeName("Shoe-" + i1);
                }
                callback.onResult(valueList, 0, totalCount);
            }
        });
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Shoe> callback) {
        // 0-19 20-39 40-59
        httpRequestManager.getTestList(new HttpRequestManager.HttpSuccessListCallback<Shoe>() {
            @Override
            public void onSuccessResultList(List<Shoe> valueList, int totalCount) {
                for (int i = 0; i < valueList.size(); i++) {
                    int i1 = params.startPosition + i;
                    valueList.get(i).setShoeName("Shoe-" + i1);
                }
                callback.onResult(valueList);
            }
        });
    }
}
