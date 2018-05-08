package top.jplayer.baseprolibrary.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * Created by Obl on 2018/1/17.
 * top.jplayer.baseprolibrary.net
 */

public class BaseSchedule<T> implements ObservableTransformer<T, T> {
    private Scheduler upSchedule;
    private Scheduler downSchedule;

    BaseSchedule(Scheduler upSchedule, Scheduler downSchedule) {
        this.upSchedule = upSchedule;
        this.downSchedule = downSchedule;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(upSchedule)
                .observeOn(downSchedule);
    }
}
