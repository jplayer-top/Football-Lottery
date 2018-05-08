package top.jplayer.baseprolibrary.widgets.dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.FilterBean;
import top.jplayer.baseprolibrary.ui.adapter.AdapterDialogFilter;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogFragmentFilter extends BaseCustomDialogFragment {

    private AdapterDialogFilter mAdapter;
    private List<FilterBean> mBeans;

    @Override
    public int initLayout() {
        return R.layout.dialog_filter;

    }

    @Override
    protected void initView(View view) {
        String[] filterName = new String[]{"原图", "夏日", "沐晴", "流年", "晨光", "碧波", "精灵", "白露", "冷调", "马提尼", "经典"};
        int[] filterPng = new int[]{
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite,
                R.color.navajowhite};
        mBeans = new ArrayList<>();
        for (int i = 0; i < filterName.length; i++) {
            mBeans.add(new FilterBean(filterName[i], filterPng[i], i == 0));
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new AdapterDialogFilter(R.layout.adapter_sample_01, mBeans);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter1, view1, position) -> {
            List<FilterBean> beanList = mAdapter.getData();
            for (int i = 0; i < beanList.size(); i++) {
                beanList.get(i).isSel = (i == position);
            }
            mAdapter.notifyDataSetChanged();
        });
    }

}
