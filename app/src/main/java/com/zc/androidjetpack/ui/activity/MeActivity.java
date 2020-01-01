package com.zc.androidjetpack.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.zc.androidjetpack.R;
import com.zc.androidjetpack.base.BaseActivity;

import butterknife.BindView;

public class MeActivity extends BaseActivity {

    @BindView(R.id.me_fragment)
    FrameLayout mFrameLayout;
    private NavController navController;

    @Override
    public int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        navController = Navigation.findNavController(mFrameLayout);
        Navigation.setViewNavController(mFrameLayout, navController);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        switch (title) {
            case "EditUserFragment":
                navController.navigate(R.id.fragmentEditUser);
                break;
            case "UpdatePwdFragment":
                navController.navigate(R.id.fragmentUpdatePwd);
                break;
            case "AboutWeFragment":
                navController.navigate(R.id.fragmentAboutWe);
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.me_fragment);
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
