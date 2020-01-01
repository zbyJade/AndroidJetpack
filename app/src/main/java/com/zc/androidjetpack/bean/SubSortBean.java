package com.zc.androidjetpack.bean;

import androidx.annotation.Nullable;

import java.util.Objects;

public class SubSortBean {
    private String subSortName;
    private String subSortId;

    public String getSubSortName() {
        return subSortName;
    }

    public void setSubSortName(String subSortName) {
        this.subSortName = subSortName;
    }

    public String getSubSortId() {
        return subSortId;
    }

    public void setSubSortId(String subSortId) {
        this.subSortId = subSortId;
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
        SubSortBean dataBean = (SubSortBean) obj;
        return Objects.equals(subSortId, dataBean.subSortId) &&
                Objects.equals(subSortName, dataBean.subSortName);
    }
}
