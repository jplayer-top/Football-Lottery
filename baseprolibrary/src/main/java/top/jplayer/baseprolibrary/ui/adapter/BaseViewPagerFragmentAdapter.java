package top.jplayer.baseprolibrary.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Obl on 2018/1/23.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public abstract class BaseViewPagerFragmentAdapter<T> extends FragmentPagerAdapter {
    protected List<T> data;

    public BaseViewPagerFragmentAdapter(FragmentManager fm, List<T> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }


}
