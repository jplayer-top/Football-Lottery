package top.jplayer.baseprolibrary.mvp.model.bean;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.mvp.model.bean
 */

public class FilterBean extends BaseBean {
    public String title;
    public int res;
    public boolean isSel;

    public FilterBean(String title, int res, boolean isSel) {
        this.title = title;
        this.res = res;
        this.isSel = isSel;
    }
}
