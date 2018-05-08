package top.jplayer.baseprolibrary.mvp.model;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.mvp.model.bean.GradBean;
import top.jplayer.baseprolibrary.mvp.model.bean.LoginBean;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.net.ApiService;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/13.
 * top.jplayer.baseprolibrary.mvp.model
 */

public class SampleModel {
    public Observable<SampleBean> requestHBList() {
        String time = String.valueOf(new Date().getTime());
        return RetrofitManager.init().create(ApiService.class)
                .getSampleBean("{\"information\":\"bd_web_api\",\"command\":\"redhallwill\",\"platform\":\"html\"," +
                                "\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", time,
                        String.format(Locale.CHINA, "Zepto%s", time))
                .compose(new IoMainSchedule<>());
    }

    public Observable<GradBean> requestGrad(String id, String userNo) {
        String time = String.valueOf(new Date().getTime());
        String parameter = String.format(Locale.CHINA, "{\"information\":\"bd_web_api\",\"command\":\"grab\",\"userno\":\"%s\",\"id\":\"%s\",\"platform\":\"html\",\"version\":\"5.2.36\",\"productName\":\"lzcp\"}", userNo, id);
        return RetrofitManager.init().create(ApiService.class)
                .getGradBean(parameter, time, String.format(Locale.CHINA, "Zepto%s", time))
                .compose(new IoMainSchedule<>());
    }

    public Observable<GradBean> requestGet(String id, String userNo) {
        String time = String.valueOf(new Date().getTime());
        String parameter = String.format(Locale.CHINA,
                "{\"information\":\"bd_web_api\",\"command\":\"reddetail\",\"userno\":\"%s\",\"id\":\"%s\",\"start\":0,\"size\":50,\"platform\":\"html\",\"version\":\"5.2.30\",\"productName\":\"lzcp\"}", userNo, id);
        return RetrofitManager.init().create(ApiService.class)
                .getGradBean(parameter, time, String.format(Locale.CHINA, "Zepto%s", time))
                .compose(new IoMainSchedule<>());
    }

    public Observable<LoginBean> login(String phone, String password) {
        String time = String.valueOf(new Date().getTime());

        int i = new Random().nextInt(900) + 100;
        String imei = String.valueOf(i);
        String parameter = String.format(Locale.CHINA,
                "{\"command\":\"login\",\"requestType\":\"login\",\"userName\":\"%s\",\"password\":\"%s\",\"productName\":\"lzcp\",\"channel\":\"1020025\",\"platform\":\"android\",\"imei\":\"869271010208%s\",\"version\":\"5.2.30\"}", phone, password, imei);
        return RetrofitManager.init().create(ApiService.class)
                .getLoginBean(parameter, time, String.format(Locale.CHINA, "jQuery111107794920853339136_%s", time))
                .compose(new IoMainSchedule<>());
    }

}

