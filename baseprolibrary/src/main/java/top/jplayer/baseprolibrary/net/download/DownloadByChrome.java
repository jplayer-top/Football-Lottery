package top.jplayer.baseprolibrary.net.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.lang.ref.WeakReference;

/**
 * Created by Obl on 2018/2/2.
 * top.jplayer.baseprolibrary.net.download
 */

public class DownloadByChrome {
    public static void byChrome(Context context, Uri uri) {
        WeakReference<Context> weakReference = new WeakReference<Context>(context);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(uri);
        weakReference.get().startActivity(intent);
    }
}
