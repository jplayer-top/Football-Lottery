package top.jplayer.baseprolibrary.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.FilterBean;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class AdapterDialogFilter extends BaseQuickAdapter<FilterBean, BaseViewHolder> {


    public AdapterDialogFilter(int layoutResId, List<FilterBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterBean item) {
        helper.setText(R.id.tvTitle, item.title)
                .setImageResource(R.id.ivSrc, item.res)
                .setTextColor(R.id.tvTitle, item.isSel ? R.color.greenyellow : R.color.white)
                .setVisible(R.id.ivBg, item.isSel);
    }
}
