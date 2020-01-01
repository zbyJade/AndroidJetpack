package com.zc.androidjetpack.ui.fragment.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdatePwdFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_update_pwd;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        setTitleText("修改密码");
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }
}
