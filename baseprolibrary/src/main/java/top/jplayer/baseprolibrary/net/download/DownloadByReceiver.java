package top.jplayer.baseprolibrary.net.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Arrays;

/**
 * Created by Obl on 2018/3/9.
 * top.jplayer.baseprolibrary.net.download
 */

public class DownloadByReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)) {
            System.out.println("用户点击了通知");

            // 点击下载进度通知时, 对应的下载ID以数组的方式传递
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            System.out.println("ids: " + Arrays.toString(ids));

        } else if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            System.out.println("下载完成");

            /*
             * 获取下载完成对应的下载ID, 这里下载完成指的不是下载成功, 下载失败也算是下载完成,
             * 所以接收到下载完成广播后, 还需要根据 id 手动查询对应下载请求的成功与失败.
             */
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
            System.out.println("id: " + id);

            // 根据获取到的ID，使用上面第3步的方法查询是否下载成功
        }
    }
}
