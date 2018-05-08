package top.jplayer.baseprolibrary.net;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Obl on 2018/1/17.
 * top.jplayer.baseprolibrary.net
 */

public class IoMainSchedule<T> extends BaseSchedule<T> {
    public IoMainSchedule() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
