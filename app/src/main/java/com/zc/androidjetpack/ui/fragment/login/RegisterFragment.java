package com.zc.androidjetpack.ui.fragment.login;

import android.os.Bundle;
import android.view.View;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;

public class RegisterFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public boolean isShowToolBar() {
        return false;
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav_back();
            }
        });
    }

    @Override
    public void initData() {

    }
}
