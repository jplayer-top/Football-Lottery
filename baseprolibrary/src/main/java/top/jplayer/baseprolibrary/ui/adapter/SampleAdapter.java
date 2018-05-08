package top.jplayer.baseprolibrary.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;

/**
 * Created by Administrator on 2018/1/29.
 * 样例
 */

public class SampleAdapter extends BaseQuickAdapter<SampleBean.DataBean.ListBean, BaseViewHolder> {
    public SampleAdapter(List<SampleBean.DataBean.ListBean> sampleBeans) {
        super(R.layout.adapter_sample_00, sampleBeans);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleBean.DataBean.ListBean item) {
    }
}
