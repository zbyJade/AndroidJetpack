package com.zc.androidjetpack.ui.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseActivity;
import com.zc.androidjetpack.utils.PermissionUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_fragment)
    FrameLayout mFrameLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        nav_controller(mFrameLayout);
    }

    @Override
    public void initData() {
        initPermission(PermissionUtil.PermissionsApp);
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    public void initPermission(String... permissions) {
        ArrayList<String> toApplyList = new ArrayList<String>();
        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 0);
        }
    }
}
