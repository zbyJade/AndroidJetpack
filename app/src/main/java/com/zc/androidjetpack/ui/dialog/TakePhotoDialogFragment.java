package com.zc.androidjetpack.ui.dialog;

import android.os.Bundle;
import android.view.View;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseDialogFragment;

import butterknife.OnClick;

/**
 * Created by ${USER_NAME} on 2018/11/5.
 */
public class TakePhotoDialogFragment extends BaseDialogFragment {

    public interface OnTakePhotoListener {
        void takePhoto();

        void albumForm();
    }

    private OnTakePhotoListener mOnTakePhotoListener;

    public TakePhotoDialogFragment setOnTakePhotoListener(OnTakePhotoListener onTakePhotoListener) {
        mOnTakePhotoListener = onTakePhotoListener;
        return this;
    }

    public static TakePhotoDialogFragment newInstance() {
        TakePhotoDialogFragment fragment = new TakePhotoDialogFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_take_photo;
    }

    @Override
    public void initViews(View view,Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_cancel, R.id.tv_albumForm, R.id.tv_takePhoto})
    protected void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_albumForm:
                mOnTakePhotoListener.albumForm();
                break;
            case R.id.tv_takePhoto:
                mOnTakePhotoListener.takePhoto();
                break;
        }
    }
}
