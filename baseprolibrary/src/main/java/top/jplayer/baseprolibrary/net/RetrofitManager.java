package top.jplayer.baseprolibrary.net;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.jplayer.baseprolibrary.BaseInitApplication;
import top.jplayer.baseprolibrary.BuildConfig;
import top.jplayer.baseprolibrary.net.cookie.CookieJarImpl;
import top.jplayer.baseprolibrary.net.cookie.OKhttpCookieChange;
import top.jplayer.baseprolibrary.net.interceptor.CommentHearderInterceptor;
import top.jplayer.baseprolibrary.net.interceptor.CommentQueryInterceptor;
import top.jplayer.baseprolibrary.net.interceptor.JsonFixInterceptor;
import top.jplayer.baseprolibrary.net.interceptor.LoggerInterceptor;
import top.jplayer.baseprolibrary.net.interceptor.ResetUrlInterceptor;
import top.jplayer.baseprolibrary.utils.GsonUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;

/**
 * Created by Obl on 2018/1/12.
 * top.jplayer.baseprolibrary.net
 */

public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;
    private OkHttpClient.Builder mBuilder;

    public static synchronized RetrofitManager init() {

        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public void build(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.setGsonFilter()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mBuilder.build())
                .build();
    }

    @NonNull
    public RetrofitManager client(Context context) {
        client(context, BaseInitApplication.TIME_OUT, interceptors());
        return this;
    }

    @NonNull
    public RetrofitManager client(Context context, Long timeOut) {
        client(context, timeOut, interceptors());
        return this;
    }

    @NonNull
    public RetrofitManager client(Context context, List<Interceptor> interceptors) {
        client(context, BaseInitApplication.TIME_OUT, interceptors);
        return this;
    }

    @NonNull
    public RetrofitManager client(Context context, Long timeOut, List<Interceptor> interceptors) {
        mBuilder = new OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(new CookieJarImpl(new OKhttpCookieChange(context)));
        Observable.fromIterable(interceptors)
                .subscribe(interceptor -> mBuilder.addInterceptor(interceptor));
        return this;
    }

    /**
     * 默认添加的拦截器
     *
     * @return
     */
    private List<Interceptor> interceptors() {
        List<Interceptor> list = new ArrayList<>();
        list.add(new CommentQueryInterceptor());
        list.add(new CommentHearderInterceptor());
        list.add(new ResetUrlInterceptor());
        list.add(new JsonFixInterceptor());
        list.add(new LoggerInterceptor(LogUtil::net, BuildConfig.DEBUG));
        return list;
    }

    public <T> T create(Class<T> reqServer) throws NullPointerException {
        return mRetrofit.create(reqServer);
    }
}
