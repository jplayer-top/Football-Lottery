package top.jplayer.bestwork;

import android.widget.FrameLayout;

import java.util.ArrayList;

import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.ui.adapter.SampleAdapter;
import top.jplayer.baseprolibrary.utils.ActivityUtils;
import top.jplayer.bestwork.common.CommonBarActivity;
import top.jplayer.bestwork.mvpb.ui.cai.JczqActivity;

public class MainActivity extends CommonBarActivity {

    private SampleAdapter mAdapter;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).init();
    }

    @Override
    public void initAddView(FrameLayout view) {
        super.initAddView(view);
        ArrayList<SampleBean.DataBean.ListBean> sampleBeans = new ArrayList<>();
        sampleBeans.add(new SampleBean.DataBean.ListBean());
        sampleBeans.add(new SampleBean.DataBean.ListBean());
        mAdapter = new SampleAdapter(sampleBeans);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            ActivityUtils.init().start(this, JczqActivity.class);
        });
    }

    @Override
    public int initAddLayout() {
        return R.layout.activity_main;
    }
}
