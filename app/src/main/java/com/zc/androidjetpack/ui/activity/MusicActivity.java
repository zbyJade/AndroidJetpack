package com.zc.androidjetpack.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.kunminx.architecture.utils.NavigationUtils;
import com.zc.androidjetpack.R;
import com.zc.androidjetpack.navTab.SpecialTab;
import com.zc.androidjetpack.navTab.SpecialTabRound;
import com.zc.androidjetpack.base.BaseActivity;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class MusicActivity extends BaseActivity {

    private final int[] PAGE_IDS = {
            R.id.navigation_home,
            R.id.navigation_music,
            R.id.navigation_me
    };
    @BindView(R.id.nav_view)
    PageNavigationView mNavigation;
    @BindView(R.id.nav_host_fragment)
    FrameLayout frameLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_music;
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
                .addItem(newItem(R.drawable.svg_nav_music_normal, R.drawable.svg_nav_music_press, "Music"))
                .addItem(newRoundItem(R.drawable.svg_nav_play_normal, R.drawable.svg_nav_play_press, "Playing"))
                .addItem(newItem(R.drawable.svg_nav_me2_normal, R.drawable.svg_nav_me2_press, "Mine"))
                .build();
        NavigationUtils.setupWithNavController(PAGE_IDS, navigationController, frameLayout);
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

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable, int checkedDrawable, String text) {
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(0xFF969696);
        mainTab.setTextCheckedColor(0xFFDC4E45);
        return mainTab;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
