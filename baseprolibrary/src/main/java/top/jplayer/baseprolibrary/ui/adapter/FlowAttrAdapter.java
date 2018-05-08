package top.jplayer.baseprolibrary.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.CartBean;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public class FlowAttrAdapter extends TagAdapter<CartBean.AttrsBean.AttrInfoBean> {

    public FlowAttrAdapter(List<CartBean.AttrsBean.AttrInfoBean> attrInfo) {
        super(attrInfo);
    }


    @Override
    public View getView(FlowLayout parent, int position, CartBean.AttrsBean.AttrInfoBean attrInfoBean) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attr, parent,
                false);
        textView.setText(attrInfoBean.attr_value);
        return textView;
    }
}