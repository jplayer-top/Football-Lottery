package top.jplayer.baseprolibrary.live.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * 普通的后台Service进程
 *
 * @author clock
 * @since 2016-04-12
 */
public class BackgroundService extends Service {

    private final static String TAG = BackgroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(LogUtil::e);
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
