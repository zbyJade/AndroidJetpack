package com.zc.androidjetpack.ui.fragment.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.ui.activity.MusicActivity;
import com.zc.androidjetpack.ui.activity.ShoeActivity;
import com.zc.androidjetpack.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
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
        Bundle arguments = getArguments();
        String account = arguments.getString("account");
        String pwd = arguments.getString("pwd");
        mEtAccount.setText(account);
        mEtPwd.setText(pwd);
    }

    @OnClick({R.id.btn_login, R.id.tv_cancel})
    public void OnViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (mEtAccount.getText().toString().equals("1")) {
                    startActivity(ShoeActivity.class);
                } else if (mEtAccount.getText().toString().equals("2")) {
                    startActivity(MusicActivity.class);
                }
                getActivity().finish();
                break;
            case R.id.tv_cancel:
                nav_back();
                break;
        }
    }

}
