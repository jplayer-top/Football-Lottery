package top.jplayer.baseprolibrary.net;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.mvp.model.bean.BaseBean;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.dialog.DialogLoading;

/**
 * Created by Obl on 2018/1/17.
 * top.jplayer.baseprolibrary.net
 */

public abstract class SampleShowDialogObserver<T extends BaseBean> implements Observer<T> {
    private Context mContext;

    public SampleShowDialogObserver(Context cxt) {
        WeakReference<Context> weakReference = new WeakReference<>(cxt);
        this.mContext = weakReference.get();
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(T t) {
        if (t.isSuccess()) {
            try {
                onRequestEnd(t);
            } catch (Exception e) {
                e.printStackTrace();
                onRequestEnd(t.msg);
            }
        } else {
            try {
                onCodeError(t);
            } catch (Exception e) {
                e.printStackTrace();
                onRequestEnd(t.msg);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.w("obl-base-error", "onError: " + e.getMessage());
        if (e.getMessage().contains("401")) {
            onRequestEnd("请先前往登录");
        } else {
            onRequestEnd("网络错误");
        }
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccess(T t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(T t) throws Exception {
        dialogDismiss(t.msg);
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
    }

    protected void onRequestStart() {
        dialogShow(mContext);
    }

    protected void onRequestEnd(String msg) {
        dialogDismiss(msg);
    }

    protected void onRequestEnd(T t) {
        dialogDismiss(t);
    }

    public DialogLoading mLoading;
    private Date mDate;
    private long mPreTime;

    public void dialogDismiss(String msg) {
        if (mLoading != null && mLoading.isShowing()) {
            long aftTime = mDate.getTime();
            long l = aftTime - mPreTime;
            Observable.timer(l < 1000 ? 1000 - l : 0, TimeUnit.MILLISECONDS)
                    .compose(new IoMainSchedule<>())
                    .subscribe(aLong -> {
                        if (mLoading != null && mLoading.isShowing()) {
                            mLoading.dismiss();
                            if (!msg.equals("")) {
                                ToastUtils.init().showInfoToast(mContext, msg);
                            }
                        }
                    });
        }
    }

    public void dialogDismiss(T t) {
        if (mLoading != null && mLoading.isShowing()) {
            long aftTime = mDate.getTime();
            long l = aftTime - mPreTime;
            Observable.timer(l < 1000 ? 1000 - l : 0, TimeUnit.MILLISECONDS)
                    .compose(new IoMainSchedule<>())
                    .subscribe(aLong -> {
                        if (mLoading != null && mLoading.isShowing()) {
                            mLoading.dismiss();
                            onSuccess(t);
                        }
                    });
        }
    }

    public void dialogShow(Context context) {
        mLoading = new DialogLoading(context);
        if (!mLoading.isShowing()) {
            mDate = new Date();
            mLoading.show();
            mPreTime = mDate.getTime();
        }
    }

}
