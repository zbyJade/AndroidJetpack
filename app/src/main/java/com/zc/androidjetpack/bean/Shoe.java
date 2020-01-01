package com.zc.androidjetpack.bean;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Shoe {
    @PrimaryKey(autoGenerate = true)//主键是否自动增长，默认为false
    private int id;
    private String shoeName;
    private String shoePic;
    private boolean isLike;

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoePic() {
        return shoePic;
    }

    public void setShoePic(String shoePic) {
        this.shoePic = shoePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            return true;
        }
        Shoe dataBean = (Shoe) obj;
        return Objects.equals(id, dataBean.id) &&
                Objects.equals(shoeName, dataBean.shoeName) &&
                Objects.equals(shoePic, dataBean.shoePic);
    }
}
