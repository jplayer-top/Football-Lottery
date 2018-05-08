package top.jplayer.baseprolibrary.listener;

import android.support.v7.widget.RecyclerView;

import top.jplayer.baseprolibrary.utils.SizeUtils;

/**
 * Created by Obl on 2018/4/9.
 * top.jplayer.baseprolibrary.listener
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class ToolBarChangeScrollListener extends RecyclerView.OnScrollListener {
    private int mDistance;
    private int maxHeight;

    public ToolBarChangeScrollListener() {
        mDistance = 0;
        maxHeight = SizeUtils.dp2px(100);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mDistance += dy;
        float percent = mDistance * 1f / maxHeight;//百分比
        if (percent > 1) {
            percent = 1;
        }
        int alpha = (int) (percent * 255);
        changeMethod(alpha, percent);
    }

    public void changeMethod(int alpha, float percent) {
//        tvBarTitle.setAlpha(percent);
//        if (maxHeight > mDistance) {
//            mToolbar.setBackgroundColor(Color.argb(alpha, 35, 159, 132));
//        }
    }

}
