package com.kunminx.architecture.ui.viewholder;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> viewArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        viewArray = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int id) {
        View view = viewArray.get(id);
        if (null == view) {
            view = itemView.findViewById(id);
            viewArray.put(id, view);
        }
        return (T) view;
    }

}
