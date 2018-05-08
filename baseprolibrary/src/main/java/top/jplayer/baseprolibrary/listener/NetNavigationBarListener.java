package top.jplayer.baseprolibrary.listener;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Obl on 2018/1/18.
 * top.jplayer.baseprolibrary.listener
 * 这可点击一次的底部栏
 */

public abstract class NetNavigationBarListener implements devlight.io.library.ntb.NavigationTabBar.OnTabBarSelectedIndexListener {
    private int lastIndex = -1;

    @Override
    public void onStartTabSelected(NavigationTabBar.Model model, int index) {
        if (lastIndex != index) {
            onceSelected(model, index);
        }
    }

    public abstract void onceSelected(NavigationTabBar.Model model, int index);

    @Override
    public void onEndTabSelected(NavigationTabBar.Model model, int index) {
        lastIndex = index;
    }
}
