package com.zc.androidjetpack.ui.fragment.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutWeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_we;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        setTitleText("关于我们");
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }
}
