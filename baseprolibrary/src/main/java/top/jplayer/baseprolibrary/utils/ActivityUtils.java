package top.jplayer.baseprolibrary.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

import top.jplayer.baseprolibrary.BaseInitApplication;
import top.jplayer.baseprolibrary.ui.Fragment.SuperBaseFragment;

/**
 * Created by Obl on 2018/1/18.
 * top.jplayer.baseprolibrary.utils
 */

public class ActivityUtils {
    private static ActivityUtils activityUtils;

    public static synchronized ActivityUtils init() {
        if (activityUtils == null) {
            synchronized (ActivityUtils.class) {
                if (activityUtils == null) {
                    activityUtils = new ActivityUtils();
                }
            }
        }
        return activityUtils;
    }

    public String getManifestPlaceholders(String key) {
         Application application = BaseInitApplication.mWeakReference.get();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = application.getPackageManager().getApplicationInfo(application
                    .getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "error key-value";
        }
        if (applicationInfo == null)
            return "not find key-value";
        return applicationInfo.metaData.getString(key);
    }

    public void start(Context context, Class tClass, MotionEvent event) {
        Intent i = new Intent(context, tClass);
        i.putExtra("x", (int) event.getX());
        i.putExtra("y", (int) event.getY());
        context.startActivity(i);
    }

    public void start(Context context, Class tClass, String title, Bundle bundle) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Intent i = new Intent(context, tClass);
        if (title != null) i.putExtra("title", title);
        if (bundle != null) i.putExtra("bundle", bundle);
        weakReference.get().startActivity(i);
    }

    public void startConversion(Context context, Class clazz, String title, String type, String mFid) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Intent intent = new Intent(context, clazz);
        intent.putExtra("title", title);
        intent.putExtra("fid", mFid.substring(2));
        intent.setAction("android.groupIntent.action.VIEW");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("rong://com.ilanchuang.xiaoi/conversation/" + type + "/?targetId=" + mFid));
        weakReference.get().startActivity(intent);
    }

    public void start(Context context, Class tClass) {
        start(context, tClass, null, null);
    }

    public void start(Context context, Class tClass, String title) {
        start(context, tClass, title, null);
    }

    public void startForResult(Activity activity, Class tClass, String title, Bundle bundle, int requestCode) {
        Intent i = new Intent(activity, tClass);
        if (title != null) i.putExtra("title", title);
        if (bundle != null) i.putExtra("bundle", bundle);
        activity.startActivityForResult(i, requestCode);
    }

    public void startForResult(Activity activity, Class tClass, String title, int requestCode) {
        startForResult(activity, tClass, title, null, requestCode);
    }

    public void startForResult(Activity activity, Class tClass, int requestCode) {
        startForResult(activity, tClass, null, null, requestCode);
    }

    /**
     * 默认为 requestCode = 1
     */
    public void startForResult(Activity activity, Class tClass, String title) {
        startForResult(activity, tClass, title, null, 1);
    }

    public void startFragmentForResult(SuperBaseFragment fragment, Class tClass, String title, int requestCode) {
        Intent intent = new Intent();
        if (title != null) intent.putExtra("title", title);
        intent.setClass(fragment.getActivity(), tClass);
        fragment.startActivityForResult(intent, requestCode);
    }


    /**
     * 默认为 requestCode = 1
     */
    public void startForResult(Activity activity, Class tClass) {
        startForResult(activity, tClass, null, null, 1);
    }
}
