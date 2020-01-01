package com.zc.androidjetpack.ui.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.kunminx.architecture.utils.LQRPhotoSelectUtils;
import com.kunminx.architecture.utils.SPUtils;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseFragment;
import com.zc.androidjetpack.ui.dialog.TakePhotoDialogFragment;
import com.zc.androidjetpack.utils.ParamsUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class EditUserFragment extends BaseFragment implements TakePhotoDialogFragment.OnTakePhotoListener {
    @BindView(R.id.iv_update_avatar)
    ImageView mIvUpdateAvatar;
    @BindView(R.id.et_name_value)
    TextInputEditText mEtNameValue;
    private TakePhotoDialogFragment takePhotoDialogFragment;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private String avatarPath;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_edit_user;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        setTitleText("编辑资料");
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        String spNickName = SPUtils.getInstance().getString(ParamsUtil.NickName);
        String spAvatarPath = SPUtils.getInstance().getString(ParamsUtil.AvatarPath);
        if (!TextUtils.isEmpty(spAvatarPath)) {
            Glide.with(getAppContext()).load(spAvatarPath).circleCrop().into(mIvUpdateAvatar);
        }
        mEtNameValue.setText(spNickName);
    }

    @Override
    public void initData() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        // 4、当拍照或从图库选取图片成功后回调
        //true裁剪，false不裁剪
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(getAppContext(), new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(final File outputFile, Uri outputUri) {
                takePhotoDialogFragment.dismiss();
                // 4、当拍照或从图库选取图片成功后回调
                avatarPath = outputFile.getPath();
                Glide.with(mIvUpdateAvatar.getContext()).load(outputFile.getPath()).circleCrop().into(mIvUpdateAvatar);
            }
        }, true);
    }

    @OnClick({R.id.iv_update_avatar, R.id.btn_save})
    public void OnViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_update_avatar:
                takePhotoDialogFragment = TakePhotoDialogFragment.newInstance();
                takePhotoDialogFragment.setOnTakePhotoListener(this);
                takePhotoDialogFragment.show(getChildFragmentManager());
                break;
            case R.id.btn_save:
                SPUtils.getInstance().put(ParamsUtil.AvatarPath, avatarPath);
                SPUtils.getInstance().put(ParamsUtil.NickName, mEtNameValue.getText().toString());
                getSharedViewModel().sharedListener.postValue(mEtNameValue.getText().toString());
                getAppContext().finish();
                break;
        }
    }

    @Override
    public void takePhoto() {
        // 2
        mLqrPhotoSelectUtils.takePhoto();
    }

    @Override
    public void albumForm() {
        // 1
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

}
