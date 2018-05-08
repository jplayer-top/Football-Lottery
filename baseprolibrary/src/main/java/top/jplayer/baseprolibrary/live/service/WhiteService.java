package top.jplayer.baseprolibrary.live.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.BuildConfig;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.LogUtil;


/**
 * 正常的系统前台进程，会在系统通知栏显示一个Notification通知图标
 *
 * @author clock
 * @since 2016-04-12
 */
public class WhiteService extends Service {

    private final static String TAG = WhiteService.class.getSimpleName();

    private final static int FOREGROUND_ID = 1000;

    @Override
    public void onCreate() {
        Log.i(TAG, "WhiteService->onCreate");
        Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(LogUtil::e);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "WhiteService->onStartCommand");
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon_set);
        builder.setContentTitle("Foreground");
        builder.setContentText("I am a foreground service");
        builder.setContentInfo("Content Infos");
        builder.setWhen(System.currentTimeMillis());
        Intent activityIntent = new Intent(BuildConfig.APPLICATION_ID + ".main.live");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        startForeground(FOREGROUND_ID, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "WhiteService->onDestroy");
        super.onDestroy();
    }
}
