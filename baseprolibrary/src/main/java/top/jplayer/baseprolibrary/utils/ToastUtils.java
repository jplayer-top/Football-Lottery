package top.jplayer.baseprolibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import top.jplayer.baseprolibrary.BaseInitApplication;

/**
 * Created by Administrator on 2018/1/27.
 */

public class ToastUtils {
    private static ToastUtils toastUtils;

    public static synchronized ToastUtils init() {
        if (toastUtils == null) {
            synchronized (ToastUtils.class) {
                if (toastUtils == null) {
                    toastUtils = new ToastUtils();
                }
            }
        }
        return toastUtils;
    }

    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime = 0;

    public void showInfoToast(Context context, String msg) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        context = contextWeakReference.get();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            Toast toast = Toasty.info(context, msg, Toast.LENGTH_SHORT, true);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            lastClickTime = currentTime;
        }
    }

    public void showSuccessToast(Context context, String msg) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        context = contextWeakReference.get();
        Toast toast = Toasty.success(context, msg, Toast.LENGTH_SHORT, true);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showErrorToast(Context context, String msg) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        context = contextWeakReference.get();
        Toast toast = Toasty.error(context, msg, Toast.LENGTH_SHORT, true);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private Toast toast;

    public void showQuickToast(Context context, String msg) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (toast == null) {
            toast = Toast.makeText(weakReference.get(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    public void showQuickToast(String msg) {
        showQuickToast(BaseInitApplication.getContext(), msg);
    }
}
