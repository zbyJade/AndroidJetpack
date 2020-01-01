package com.zc.androidjetpack.ui.fragment.login;

import android.os.Bundle;
import android.view.View;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;

import butterknife.OnClick;

public class WelcomeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    public boolean isShowToolBar() {
        return false;
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Bundle bundle = new Bundle();
                bundle.putString("account", "1");
                bundle.putString("pwd", "123456");
                nav_bundle(R.id.action_welcomeFragment_to_loginFragment, bundle);
                break;
            case R.id.btn_register:
                nav(R.id.action_welcomeFragment_to_registerFragment);
                break;
        }
    }
}
