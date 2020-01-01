package com.zc.androidjetpack.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.db.ShoeDatabase;
import com.zc.androidjetpack.db.dao.ShoeDao;
import com.zc.androidjetpack.utils.ParamsUtil;

import java.util.List;

public class ShoeRepository {
    private ShoeDao mShoeDao;

    public ShoeRepository(Application application) {
        ShoeDatabase db = ShoeDatabase.getInstance(application);
        mShoeDao = db.getShoeDao();
    }

    public LiveData<PagedList<Shoe>> getAllShoes() {
        DataSource.Factory<Integer, Shoe> factory = mShoeDao.getAllShoes();
        return new LivePagedListBuilder<>(factory, ParamsUtil.SIZE).build();
    }

    public LiveData<PagedList<Shoe>> getLikeShoes() {
        DataSource.Factory<Integer, Shoe> factory = mShoeDao.getLikeShoes(true);
        return new LivePagedListBuilder<>(factory, ParamsUtil.SIZE).build();
    }

    public void updateShoe(Shoe shoe) {
        AsyncTask.execute(() -> mShoeDao.update(shoe));
    }

    public void deleteShoe(Shoe shoe) {
        AsyncTask.execute(() -> mShoeDao.delete(shoe));
    }

    public void insertShoe(Shoe shoe) {
        new insertShoeAsync(mShoeDao).execute(shoe);
    }

    public void insertShoes(List<Shoe> shoes) {
        new insertShoesAsync(mShoeDao).execute(shoes);
    }

    private static class insertShoeAsync extends AsyncTask<Shoe, Void, Long> {

        private ShoeDao mAnimalDaoAsync;

        insertShoeAsync(ShoeDao noteDao) {
            mAnimalDaoAsync = noteDao;
        }

        @Override
        protected Long doInBackground(Shoe... shoes) {
            mAnimalDaoAsync.insert(shoes[0]);
            return null;
        }
    }

    private static class insertShoesAsync extends AsyncTask<List<Shoe>, Void, Void> {

        private ShoeDao mAnimalDaoAsync;

        insertShoesAsync(ShoeDao noteDao) {
            mAnimalDaoAsync = noteDao;
        }

        @Override
        protected Void doInBackground(List<Shoe>... lists) {
            mAnimalDaoAsync.insertShoes(lists[0]);
            return null;
        }
    }
}
