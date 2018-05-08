package top.jplayer.bestwork;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import top.jplayer.baseprolibrary.BaseInitApplication;

/**
 * Created by Obl on 2018/4/25.
 * top.jplayer.bestwork
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class BestWorkApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BaseInitApplication.with(this)
                .addUrl("host_cai_dc","https://dc.leader001.cn/")
                .addUrl("host_cai_m","https://m.leader001.cn/")
                .retrofit(BuildConfig.HOST);
    }
}
