package top.jplayer.baseprolibrary.ui.Fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.LogUtil;

import static top.jplayer.baseprolibrary.utils.StringUtils.getRandColorCode;

/**
 * Created by Obl on 2018/1/23.
 * top.jplayer.baseprolibrary.ui.Fragment
 */

public class TestFragment extends SuperBaseFragment {
    @Override
    protected void initData(View rootView) {
        rootView.setBackgroundColor(Color.parseColor(getRandColorCode()));
        TextView base_text = rootView.findViewById(R.id.base_text);
        base_text.setText(getClass().getSimpleName());
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_base;
    }

    @Override
    protected void onShowFragment() {
        super.onShowFragment();
        LogUtil.e("isShow---");
    }


}
