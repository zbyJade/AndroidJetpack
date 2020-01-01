package com.zc.androidjetpack.ui.fragment.shoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.kunminx.architecture.utils.SPUtils;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;
import com.zc.androidjetpack.ui.activity.LoginActivity;
import com.zc.androidjetpack.ui.activity.MeActivity;
import com.zc.androidjetpack.utils.ParamsUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentMe extends BaseFragment {

    @BindView(R.id.tv_nickName)
    TextView mTvNickName;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
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
        getSharedViewModel().sharedListener.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String spNickName = SPUtils.getInstance().getString(ParamsUtil.NickName);
                String spAvatarPath = SPUtils.getInstance().getString(ParamsUtil.AvatarPath);
                mTvNickName.setText(TextUtils.isEmpty(spNickName) ? s : spNickName);
                if (!TextUtils.isEmpty(spAvatarPath)) {
                    Glide.with(mIvAvatar.getContext()).load(spAvatarPath).circleCrop().into(mIvAvatar);
                }
            }
        });
    }

    @OnClick({R.id.tv_edit_info, R.id.pwd_layout, R.id.about_layout, R.id.btn_logout})
    public void OnViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_edit_info:
                bundle.putString("title", "EditUserFragment");
                startActivity(new Intent(getAppContext(), MeActivity.class).putExtras(bundle));
                break;
            case R.id.pwd_layout:
                bundle.putString("title", "UpdatePwdFragment");
                startActivity(new Intent(getAppContext(), MeActivity.class).putExtras(bundle));
                break;
            case R.id.about_layout:
                bundle.putString("title", "AboutWeFragment");
                startActivity(new Intent(getAppContext(), MeActivity.class).putExtras(bundle));
                break;
            case R.id.btn_logout:
                startAndClearHistoryActivity(LoginActivity.class);
                break;
        }
    }
}
