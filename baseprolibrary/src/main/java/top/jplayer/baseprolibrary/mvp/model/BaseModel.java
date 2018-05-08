package top.jplayer.baseprolibrary.mvp.model;

import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/4/26.
 * top.jplayer.bestwork.mvpb.model
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BaseModel<T> {

    public T server;

    public BaseModel(Class<T> t) {
        server = RetrofitManager.init().create(t);
    }
}
