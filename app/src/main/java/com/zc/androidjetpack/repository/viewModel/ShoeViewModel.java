package com.zc.androidjetpack.repository.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.repository.ShoeRepository;
import com.zc.androidjetpack.repository.factory.ShoeFactory;
import com.zc.androidjetpack.utils.ParamsUtil;

import java.util.List;

public class ShoeViewModel extends AndroidViewModel {

    private final LiveData<PagedList<Shoe>> shoeList;
    private DataSource<Integer, Shoe> shoeBeanDataSource;
    private final ShoeRepository shoeRepository;

    public ShoeViewModel(@NonNull Application application) {
        super(application);
        shoeRepository = new ShoeRepository(application);
        ShoeFactory shoeFactory = new ShoeFactory();
        shoeBeanDataSource = shoeFactory.create();
        shoeList = new LivePagedListBuilder<>(shoeFactory, ParamsUtil.SIZE).build();
    }

    public void invalidateDataSource() {
        shoeBeanDataSource.invalidate();
    }

    public LiveData<PagedList<Shoe>> getShoeList() {
        return shoeList;
    }

    public LiveData<PagedList<Shoe>> getLikeShoes() {
        return shoeRepository.getLikeShoes();
    }

    public void insertShoes(List<Shoe> shoes) {
        shoeRepository.insertShoes(shoes);
    }

    public void insertShoe(Shoe shoe) {
        shoeRepository.insertShoe(shoe);
    }

    public void updateShoe(Shoe shoe) {
        shoeRepository.updateShoe(shoe);
    }

    public void deleteShoe(Shoe shoe) {
        shoeRepository.deleteShoe(shoe);
    }
}
