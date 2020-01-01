package com.zc.androidjetpack.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.kunminx.architecture.ui.adapter.CommonViewPagerAdapter;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.navTab.SpecialTab;
import com.zc.androidjetpack.base.BaseActivity;
import com.zc.androidjetpack.ui.fragment.shoe.FragmentHome;
import com.zc.androidjetpack.ui.fragment.shoe.FragmentLike;
import com.zc.androidjetpack.ui.fragment.shoe.FragmentMe;
import com.zc.androidjetpack.ui.fragment.shoe.FragmentSorts;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class ShoeActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    PageNavigationView mNavigation;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    String[] titles = {"Shoes", "Sorts", "Likes", "Mine"};
    private Fragment[] fragments = {new FragmentHome(), new FragmentSorts(), new FragmentLike(), new FragmentMe()};

    @Override
    public int getLayoutId() {
        return R.layout.activity_shoe;
    }

    @Override
    public void initViews(View view, Bundle savedInstanceState) {
        initBottomNavigation(mNavigation);
    }

    @Override
    public void initData() {

    }

    /**
     * 初始化底部导航
     */
    private void initBottomNavigation(PageNavigationView pageNavigationView) {
        NavigationController navigationController = pageNavigationView.custom()
                .addItem(newItem(R.drawable.svg_nav_shoe_normal, R.drawable.svg_nav_shoe_press, titles[0]))
                .addItem(newItem(R.drawable.svg_nav_sorts_normal, R.drawable.svg_nav_sorts_press, titles[1]))
                .addItem(newItem(R.drawable.svg_nav_like_normal, R.drawable.svg_nav_like_press, titles[2]))
                .addItem(newItem(R.drawable.svg_nav_me_normal, R.drawable.svg_nav_me_press, titles[3]))
                .build();

        mViewPager.setAdapter(new CommonViewPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPager.setOffscreenPageLimit(3);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(mViewPager);
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(0xFF969696);
        mainTab.setTextCheckedColor(0xFFDC4E45);
        return mainTab;
    }

}
