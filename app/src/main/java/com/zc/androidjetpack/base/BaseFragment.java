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

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.kunminx.architecture.data.manager.NetState;
import com.kunminx.architecture.data.manager.NetworkStateManager;
import com.zc.androidjetpack.App;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.db.ShoeDatabase;
import com.zc.androidjetpack.repository.viewModel.SharedViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseInterface {

    private AppCompatActivity mActivity;
    private SharedViewModel mSharedViewModel;
    private Unbinder bind;
    LinearLayout mLeftLayout;
    TextView mTvTitle;
    private boolean isShowToolBar;
    protected ShoeDatabase mAppDatabase;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
        mAppDatabase = ((App) mActivity.getApplicationContext()).getAppDatabase();
        NetworkStateManager.getInstance().mNetworkStateCallback.observe(this, netState -> {
            //TODO 注意 liveData 的 lambda 回调中不可为空，不然会出现 Cannot add the same observer with different lifecycles 的现象，
            // 详见：https://stackoverflow.com/questions/47025233/android-lifecycle-library-cannot-add-the-same-observer-with-different-lifecycle

            onNetworkStateChanged(netState);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isShowToolBar = isShowToolBar();
        return createContentView(container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
        initViews(view, savedInstanceState);
        initData();
    }

    /**
     * 创建View
     */
    private View createContentView(ViewGroup parent) {
        LinearLayout content = new LinearLayout(mActivity);
        content.setOrientation(LinearLayout.VERTICAL);
        View mContentView = getLayoutInflater().inflate(getLayoutId(), parent, false);
        if (mContentView == null) {
            new IllegalArgumentException("getContentLayout must View or LayoutId");
        }
        if (isShowToolBar) {
            // 显示toolbar
            View toolbarView = getLayoutInflater().inflate(R.layout.toolbar_layout, parent, false);
            content.addView(toolbarView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mLeftLayout = toolbarView.findViewById(R.id.m_bar_ll_left);
            mTvTitle = toolbarView.findViewById(R.id.m_bar_tv_title);
        }
        content.addView(mContentView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        bind = ButterKnife.bind(this, content);
        return content;
    }

    public void setTitleText(String titleText) {
        mTvTitle.setText(titleText);
    }

    public void setLeftVisible(boolean visible) {
        mLeftLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public AppCompatActivity getAppContext() {
        return mActivity;
    }

    @Override
    public boolean isShowToolBar() {
        return true;
    }

    @Override
    public void initTitle() {
        if (mLeftLayout != null) {
            mLeftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    protected void onNetworkStateChanged(NetState netState) {
        //TODO 子类可以重写该方法，统一的网络状态通知和处理
    }

    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringRes) {
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    public void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    protected void nav(int id) {
        NavHostFragment.findNavController(this).navigate(id);
    }

    protected void nav_bundle(int id, Bundle bundle) {
        NavHostFragment.findNavController(this).navigate(id, bundle);
    }

    protected void nav_back() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
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
        intent.setClass(getActivity(), targetActivity);
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
        intent.setClass(getActivity(), targetActivity);
        startActivity(intent);
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }

}
