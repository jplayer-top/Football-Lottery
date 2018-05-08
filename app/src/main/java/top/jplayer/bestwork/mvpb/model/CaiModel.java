package top.jplayer.bestwork.mvpb.model;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.BaseModel;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.bestwork.mvpb.AppServer;
import top.jplayer.bestwork.mvpb.bean.JczqBean;

/**
 * Created by Obl on 2018/4/26.
 * top.jplayer.bestwork.mvpb.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class CaiModel extends BaseModel<AppServer> {


    public CaiModel(Class<AppServer> t) {
        super(t);
    }

    public Observable<JczqBean> reqJczqBean() {
        return server.reqJczqBean().compose(new IoMainSchedule<>());
    }
}
