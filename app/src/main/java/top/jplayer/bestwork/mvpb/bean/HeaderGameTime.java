package top.jplayer.bestwork.mvpb.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import top.jplayer.bestwork.mvpb.ui.adapter.JczqExpandAdapter;

/**
 * Created by Obl on 2018/4/27.
 * top.jplayer.bestwork.mvpb.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class HeaderGameTime extends AbstractExpandableItem<JczqBean.ResultBean> implements MultiItemEntity {
    public String tvTime;
    public String tvCountGame;

    public HeaderGameTime(String tvTime, String tvCountGame) {
        this.tvTime = tvTime;
        this.tvCountGame = tvCountGame;
    }

    @Override
    public int getItemType() {
        return JczqExpandAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
