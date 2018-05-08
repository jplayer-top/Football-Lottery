package top.jplayer.baseprolibrary.net.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by Administrator on 2018/1/26.
 * json 解析自定义，如果返回的Json并不是规范的，可自行修改 method: getJsonBody(String sting)
 */

public class JsonFixInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        response = decrypt(response);
        return response;
    }

    private Response decrypt(Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {
                BufferedSource source = body.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                MediaType contentType = body.contentType();
                String string = buffer.clone().readString(UTF8);
                String bodyString = getJsonBody(string);
                ResponseBody responseBody = ResponseBody.create(contentType, bodyString);
                response = response.newBuilder().body(responseBody).build();
            }
            return response;
        }
        return response;
    }

    /**
     * @param string 原生body
     * @return 修复的body
     */
    @NonNull
    public String getJsonBody(String string) {
        String bodyString = string;
        if (string.contains("(") && string.contains(")")) {
            bodyString = string.substring(string.indexOf("(") + 1, string.lastIndexOf(")"));
        }
        return bodyString;
    }

}
