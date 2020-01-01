package com.zc.androidjetpack.bean;

import java.util.List;
import java.util.Objects;

public class NetBean {

    private int status;
    private String message;
    private int totalPages;
    private int totalRowsCount;
    private Object tip;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRowsCount() {
        return totalRowsCount;
    }

    public void setTotalRowsCount(int totalRowsCount) {
        this.totalRowsCount = totalRowsCount;
    }

    public Object getTip() {
        return tip;
    }

    public void setTip(Object tip) {
        this.tip = tip;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String title;
        private String create_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() == obj.getClass()) {
                return true;
            }
            DataBean dataBean = (DataBean) obj;
            return Objects.equals(id, dataBean.id) &&
                    Objects.equals(title, dataBean.title) &&
                    Objects.equals(create_date, dataBean.create_date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, create_date);
        }
    }
}
