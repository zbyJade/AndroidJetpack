package com.zc.androidjetpack.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zc.androidjetpack.bean.Shoe;
import com.zc.androidjetpack.repository.dataSource.ShoeDataSource;

import java.util.List;

@Dao
public interface ShoeDao {
    @Query("SELECT * FROM shoe")
    ShoeDataSource.Factory<Integer, Shoe> getAllShoes();

    @Query("SELECT * FROM shoe WHERE isLike=:isLike")
    ShoeDataSource.Factory<Integer, Shoe> getLikeShoes(boolean isLike);

    @Insert
    void insert(Shoe... shoes);

    @Insert
    void insertShoes(List<Shoe> shoeLists);

    @Update
    void update(Shoe... shoes);

    @Delete
    void delete(Shoe... shoes);
}
