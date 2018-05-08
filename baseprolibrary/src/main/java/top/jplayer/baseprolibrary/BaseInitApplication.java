package top.jplayer.baseprolibrary;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.ArrayMap;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import okhttp3.Interceptor;
import top.jplayer.baseprolibrary.listener.SampleApplicationLifecycleCallbacks;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary
 */

public class BaseInitApplication {


    private static BaseInitApplication mInit;
    public static WeakReference<Application> mWeakReference;
    public static Map<String, String> mUrlMap;
    public static final Long TIME_OUT = 30L;
    public static List<Activity> sActivityList;

    private BaseInitApplication(Application application) {
        mWeakReference = new WeakReference<>(application);
        mUrlMap = new ArrayMap<>();
        sActivityList = new LinkedList<>();
    }

    public static Context getContext() {
        return mWeakReference.get().getApplicationContext();
    }

    public Application getApplication() {
        return mWeakReference.get();
    }


    public synchronized static BaseInitApplication with(Application application) {
        if (mInit == null) {

            synchronized (BaseInitApplication.class) {
                if (mInit == null) {
                    mInit = new BaseInitApplication(application);
                }
            }
        }
        return mInit;
    }

    /**
     * Activity管理器
     */
    public BaseInitApplication lifecycle() {
        getApplication().registerActivityLifecycleCallbacks(mCallbacks);
        return this;
    }

    /**
     * 是否开启仿IOS 滑动返回
     *
     * @return
     */
    public BaseInitApplication swipeBack() {
        BGASwipeBackHelper.init(getApplication(), null);
        return this;
    }

    /**
     * 修复7.0以上的file provide
     *
     */
    public BaseInitApplication fixFileProvide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        return this;
    }

    /**
     * 你项目的主Host
     * client 方法中已经添加了一些常用过滤器
     *
     * @param host "https://www.jplayer.top/"
     */
    public BaseInitApplication retrofit(String host) {
        RetrofitManager.init().client(getApplication()).build(host);
        return this;
    }

    /**
     * 添加一些自定义的过滤器
     *
     * @param interceptors 过滤器
     */
    public BaseInitApplication retrofit(String host, Interceptor... interceptors) {
        RetrofitManager.init().client(getApplication(), Arrays.asList(interceptors)).build(host);
        return this;
    }


    /**
     * 添加单个url
     * 例如  addUrl("host_weather","https://www.weathser.com/")
     * 然后在你的ApiServer 下使用到的接口处 添加头 @Headers({"HEADER_HOST:host_weather"})
     */
    public BaseInitApplication addUrl(String key, String value) {
        mUrlMap.put(key, value);
        return this;
    }

    /**
     * 添加集合url
     */
    public BaseInitApplication urlMap(Map<String, String> map) {
        mUrlMap.putAll(map);
        return this;
    }

    /**
     * 初始化zxing
     */
    public BaseInitApplication zxing() {
        ZXingLibrary.initDisplayOpinion(getContext());
        return this;
    }


    private Application.ActivityLifecycleCallbacks mCallbacks = new SampleApplicationLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sActivityList.add(activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

}
