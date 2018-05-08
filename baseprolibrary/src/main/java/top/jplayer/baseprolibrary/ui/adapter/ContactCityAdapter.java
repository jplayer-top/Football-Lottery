package top.jplayer.baseprolibrary.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.ModelContactCity;

/**
 * Created by Obl on 2018/1/31.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public class ContactCityAdapter extends BaseMultiItemQuickAdapter<ModelContactCity, BaseViewHolder> {
    public ContactCityAdapter(List<ModelContactCity> data) {
        super(data);
        addItemType(0, R.layout.item_wave_contact);
        addItemType(1, R.layout.item_pinned_header);
        addItemType(2, R.layout.item_wave_contact);
    }

    @Override
    protected void convert(BaseViewHolder holder, ModelContactCity item) {
        if (holder.getItemViewType() == 0) {
            holder.setText(R.id.tv_contact_name, item.name);
        } else if (holder.getItemViewType() == 1) {
            String letter = item.pys.substring(0, 1);
            if (TextUtils.equals("#", letter)) {
                holder.setText(R.id.city_tip, item.name);
            } else {
                holder.setText(R.id.city_tip, letter);
            }
        } else {
            holder.setText(R.id.tv_contact_name, item.name).setVisible(R.id.iv, true);
        }
    }


    public int getLetterPosition(String letter) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).type == 1 && getData().get(i).pys.equals(letter)) {
                return i;
            }
        }
        return -1;
    }
}
