package com.zc.androidjetpack.base;

import android.os.Bundle;
import android.view.View;

public interface BaseInterface {

    int getLayoutId();

    boolean isShowToolBar();

    void initTitle();

    void initViews(View view, Bundle savedInstanceState);

    void initData();
}
