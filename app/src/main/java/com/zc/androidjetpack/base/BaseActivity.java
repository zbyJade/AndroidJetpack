/*
 * Copyright 2018-2019 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zc.androidjetpack.base;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.kunminx.architecture.data.manager.NetworkStateManager;
import com.kunminx.architecture.utils.AdaptScreenUtils;
import com.kunminx.architecture.utils.BarUtils;
import com.kunminx.architecture.utils.ScreenUtils;
import com.zc.androidjetpack.App;
import com.zc.androidjetpack.repository.viewModel.SharedViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {

    protected SharedViewModel mSharedViewModel;
    private Bundle mSavedInstanceState;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
        setContentView(getLayoutId());
        //ButterKnife
        bind = ButterKnife.bind(this);
        initViews(null, mSavedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public boolean isShowToolBar() {
        return false;
    }

    @Override
    public void initTitle() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) getApplicationContext()).getAppViewModelProvider(this);
    }


    protected void nav_controller(View view) {
        Navigation.setViewNavController(view, Navigation.findNavController(view));
    }

    protected void nav(View view, int id) {
        Navigation.findNavController(view).navigate(id);
    }


    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(new Intent(this, targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param data           Activity之间传递数据，Intent的Extra key为Constant.EXTRA_NAME.DATA
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull String name, @NonNull Bundle data, @NonNull Class<?> targetActivity) {
        final Intent intent = new Intent();
        intent.putExtra(name, data);
        intent.setClass(this, targetActivity);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startAndClearHistoryActivity(@NonNull Class<?> targetActivity) {
        final Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this, targetActivity);
        startActivity(intent);
    }

}
