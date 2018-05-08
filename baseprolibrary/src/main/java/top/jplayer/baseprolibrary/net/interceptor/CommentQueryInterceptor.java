package top.jplayer.baseprolibrary.net.interceptor;


import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import top.jplayer.baseprolibrary.BuildConfig;

/**
 * Created by Obl on 2018/3/13.
 * top.jplayer.baseprolibrary.net.interceptor
 * 设置公共参数 例如 version,source
 */

public class CommentQueryInterceptor implements Interceptor {
    @Override
    public Response intercept(@Nullable Chain chain) throws IOException {
        assert chain != null;
        Request request = chain.request();
        HttpUrl.Builder builder = request.url().newBuilder();
        HttpUrl httpUrl = builder
                .addQueryParameter("version", BuildConfig.VERSION_NAME)
                .addQueryParameter("source", BuildConfig.FLAVOR)
                .build();
        Request build = request.newBuilder().url(httpUrl).build();
        return chain.proceed(build);
    }
}
