package top.jplayer.baseprolibrary.ui.adapter;

import android.support.v4.util.ArrayMap;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Map;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.CartBean;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.ui.adapter
 * /你好
 */

public class AdapterDialogCart extends BaseQuickAdapter<CartBean.AttrsBean, BaseViewHolder> {
    public Map<Integer, Integer> mSelAttrMap;

    public AdapterDialogCart(int layoutResId, List<CartBean.AttrsBean> data) {
        super(layoutResId, data);
        mSelAttrMap = new ArrayMap<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CartBean.AttrsBean item) {
        helper.setText(R.id.tvFlowType, item.type_name);
        TagFlowLayout mFlowLayout = helper.itemView.findViewById(R.id.flowLayout);
        mFlowLayout.setAdapter(new FlowAttrAdapter(item.attrInfo));
        mFlowLayout.setOnTagClickListener((view, position, parent) -> {
            int layoutPosition = helper.getLayoutPosition();
            if (mSelAttrMap.containsKey(layoutPosition) && mSelAttrMap.get(layoutPosition) == item.attrInfo.get(position).attr_id) {
                mSelAttrMap.remove(layoutPosition);
            } else {
                mSelAttrMap.put(layoutPosition, item.attrInfo.get(position).attr_id);
                ToastUtils.init().showQuickToast(helper.itemView.getContext(), LogUtil.str(mSelAttrMap));
            }
            return true;
        });
    }
}
