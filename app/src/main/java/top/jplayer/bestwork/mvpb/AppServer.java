package top.jplayer.bestwork.mvpb;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import top.jplayer.bestwork.mvpb.bean.JczqBean;

/**
 * Created by Obl on 2018/4/26.
 * top.jplayer.bestwork.mvp
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public interface AppServer {

    @Headers({"HEADER_HOST:host_cai_dc"})
    @GET("racedata/race/jczq/against?raceType=02&type=")
    Observable<JczqBean> reqJczqBean();
}
