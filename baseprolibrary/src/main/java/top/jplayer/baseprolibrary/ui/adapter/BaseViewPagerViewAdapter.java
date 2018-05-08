package top.jplayer.baseprolibrary.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Obl on 2018/4/8.
 * top.jplayer.baseprolibrary.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public abstract class BaseViewPagerViewAdapter<T> extends PagerAdapter {
    public List<T> list;
    protected SparseIntArray layouts = new SparseIntArray();
    protected final static int DEF_TYPE = 0;

    public BaseViewPagerViewAdapter(List<T> list, @LayoutRes int res) {
        this.list = list;
        addItemType(DEF_TYPE, res);
    }

    public BaseViewPagerViewAdapter(List<T> list) {
        this.list = list;
    }

    protected void addItemType(int type, @LayoutRes int layoutResId) {

        layouts.put(type, layoutResId);
    }

    /**
     * 如果是多类型布局，必须重写该方法
     *
     * @param position
     * @return
     */
    public int getItemType(int position) {
        return DEF_TYPE;
    }

    private int mChildCount = 0;

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), layouts.get(getItemType(position)), null);
        bindView(view, list.get(position), position);
        container.addView(view);
        return view;
    }

    public abstract void bindView(View view, T t, int position);

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
