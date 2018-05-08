package top.jplayer.bestwork.mvpb.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import top.jplayer.bestwork.mvpb.ui.adapter.JczqExpandAdapter;

/**
 * Created by Obl on 2018/4/26.
 * top.jplayer.bestwork.mvpb.bean
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class JczqBean extends CaiBean {
    /**
     * result : [[{"unsupport":"3006_01,3010_01","homeRank":"","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"挪超杯","teamId":"001","weekId":"4","matchDate":"2018-04-27 00:30:00","awayRank":"","day":"20180426","homeTeam":"利勒斯特","week":"周四","guestTeam":"罗森博格","ishot":0,"supportAwards":"","v0":"2.03","v1":"3.20","v3":"3.08","letVs_v0":"4.20","letVs_v1":"3.75","letVs_v3":"1.60","letVs_letPoint":"+1","score_v00":"12.00","score_v01":"8.50","score_v02":"10.00","score_v03":"17.00","score_v04":"32.00","score_v05":"90.00","score_v09":"45.00","score_v10":"10.50","score_v11":"6.00","score_v12":"7.25","score_v13":"13.50","score_v14":"32.00","score_v15":"80.00","score_v20":"17.00","score_v21":"9.75","score_v22":"11.00","score_v23":"22.00","score_v24":"55.00","score_v25":"125.0","score_v30":"36.00","score_v31":"25.00","score_v32":"27.00","score_v33":"50.00","score_v40":"100.0","score_v41":"70.00","score_v42":"70.00","score_v50":"400.0","score_v51":"200.0","score_v52":"300.0","score_v90":"70.00","score_v99":"250.0","goal_v0":"12.00","goal_v1":"5.00","goal_v2":"3.40","goal_v3":"3.45","goal_v4":"5.00","goal_v5":"8.00","goal_v6":"14.00","goal_v7":"20.00","half_v00":"2.80","half_v01":"13.00","half_v03":"34.00","half_v10":"4.90","half_v11":"5.25","half_v13":"7.00","half_v30":"24.00","half_v31":"13.00","half_v33":"5.15"},{"unsupport":"3006_01","homeRank":"","videoLink":"","leagueColor":"#f34f66","endTime":"04-26 23:52","league":"欧罗巴","teamId":"002","weekId":"4","matchDate":"2018-04-27 03:05:00","awayRank":"","day":"20180426","homeTeam":"阿森纳","week":"周四","guestTeam":"马竞","ishot":1,"supportAwards":"","v0":"2.80","v1":"3.00","v3":"2.28","letVs_v0":"1.47","letVs_v1":"3.88","letVs_v3":"5.25","letVs_letPoint":"-1","score_v00":"8.00","score_v01":"7.25","score_v02":"12.50","score_v03":"30.00","score_v04":"80.00","score_v05":"250.0","score_v09":"100.0","score_v10":"6.75","score_v11":"6.00","score_v12":"9.50","score_v13":"24.00","score_v14":"70.00","score_v15":"250.0","score_v20":"10.00","score_v21":"8.25","score_v22":"13.00","score_v23":"33.00","score_v24":"100.0","score_v25":"250.0","score_v30":"21.00","score_v31":"20.00","score_v32":"29.00","score_v33":"60.00","score_v40":"60.00","score_v41":"50.00","score_v42":"80.00","score_v50":"150.0","score_v51":"125.0","score_v52":"250.0","score_v90":"70.00","score_v99":"400.0","goal_v0":"8.00","goal_v1":"3.90","goal_v2":"3.05","goal_v3":"3.90","goal_v4":"6.00","goal_v5":"11.00","goal_v6":"19.00","goal_v7":"27.00","half_v00":"4.40","half_v01":"12.00","half_v03":"23.00","half_v10":"6.00","half_v11":"4.50","half_v13":"5.50","half_v30":"25.00","half_v31":"12.00","half_v33":"3.80"},{"unsupport":"3006_01,3010_01","homeRank":"","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"欧罗巴","teamId":"003","weekId":"4","matchDate":"2018-04-27 03:05:00","awayRank":"","day":"20180426","homeTeam":"马赛","week":"周四","guestTeam":"萨尔茨堡","ishot":1,"supportAwards":"","v0":"4.50","v1":"3.75","v3":"1.56","letVs_v0":"2.14","letVs_v1":"3.60","letVs_v3":"2.60","letVs_letPoint":"-1","score_v00":"14.00","score_v01":"13.00","score_v02":"23.00","score_v03":"60.00","score_v04":"150.0","score_v05":"500.0","score_v09":"100.0","score_v10":"7.50","score_v11":"7.25","score_v12":"12.50","score_v13":"30.00","score_v14":"100.0","score_v15":"300.0","score_v20":"8.25","score_v21":"7.00","score_v22":"12.00","score_v23":"35.00","score_v24":"100.0","score_v25":"300.0","score_v30":"12.50","score_v31":"11.50","score_v32":"19.00","score_v33":"40.00","score_v40":"25.00","score_v41":"22.00","score_v42":"40.00","score_v50":"50.00","score_v51":"50.00","score_v52":"80.00","score_v90":"30.00","score_v99":"250.0","goal_v0":"14.00","goal_v1":"5.30","goal_v2":"3.45","goal_v3":"3.75","goal_v4":"4.75","goal_v5":"7.50","goal_v6":"12.00","goal_v7":"14.50","half_v00":"6.80","half_v01":"14.00","half_v03":"21.00","half_v10":"10.00","half_v11":"6.00","half_v13":"4.10","half_v30":"33.00","half_v31":"14.00","half_v33":"2.30"},{"unsupport":"3006_01,3010_01","homeRank":"13","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"解放者杯","teamId":"004","weekId":"4","matchDate":"2018-04-27 06:15:00","awayRank":"20","day":"20180426","homeTeam":"河床","week":"周四","guestTeam":"埃梅莱克","ishot":1,"supportAwards":"","v0":"8.80","v1":"4.50","v3":"1.26","letVs_v0":"3.18","letVs_v1":"3.35","letVs_v3":"1.94","letVs_letPoint":"-1","score_v00":"10.50","score_v01":"17.00","score_v02":"40.00","score_v03":"150.0","score_v04":"600.0","score_v05":"1000","score_v09":"400.0","score_v10":"5.80","score_v11":"8.00","score_v12":"21.00","score_v13":"80.00","score_v14":"400.0","score_v15":"1000","score_v20":"5.50","score_v21":"7.00","score_v22":"21.00","score_v23":"70.00","score_v24":"400.0","score_v25":"1000","score_v30":"7.50","score_v31":"10.50","score_v32":"29.00","score_v33":"100.0","score_v40":"14.00","score_v41":"21.00","score_v42":"50.00","score_v50":"30.00","score_v51":"40.00","score_v52":"100.0","score_v90":"30.00","score_v99":"600.0","goal_v0":"10.50","goal_v1":"4.50","goal_v2":"3.25","goal_v3":"3.55","goal_v4":"5.30","goal_v5":"9.00","goal_v6":"15.00","goal_v7":"25.00","half_v00":"13.00","half_v01":"19.00","half_v03":"28.00","half_v10":"16.00","half_v11":"6.00","half_v13":"3.55","half_v30":"70.00","half_v31":"19.00","half_v33":"1.75"},{"unsupport":"3006_01,3010_01","homeRank":"28","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"解放者杯","teamId":"005","weekId":"4","matchDate":"2018-04-27 06:15:00","awayRank":"9","day":"20180426","homeTeam":"克鲁塞罗","week":"周四","guestTeam":"智利大学","ishot":0,"supportAwards":"","v0":"7.10","v1":"3.95","v3":"1.36","letVs_v0":"2.70","letVs_v1":"3.25","letVs_v3":"2.22","letVs_letPoint":"-1","score_v00":"9.50","score_v01":"15.00","score_v02":"35.00","score_v03":"120.0","score_v04":"500.0","score_v05":"1000","score_v09":"400.0","score_v10":"5.50","score_v11":"7.00","score_v12":"19.00","score_v13":"70.00","score_v14":"300.0","score_v15":"900.0","score_v20":"5.50","score_v21":"7.00","score_v22":"20.00","score_v23":"70.00","score_v24":"300.0","score_v25":"900.0","score_v30":"8.50","score_v31":"11.00","score_v32":"28.00","score_v33":"100.0","score_v40":"17.00","score_v41":"23.00","score_v42":"60.00","score_v50":"40.00","score_v51":"50.00","score_v52":"120.0","score_v90":"40.00","score_v99":"600.0","goal_v0":"9.50","goal_v1":"4.10","goal_v2":"3.15","goal_v3":"3.60","goal_v4":"5.50","goal_v5":"10.50","goal_v6":"17.00","goal_v7":"29.00","half_v00":"12.00","half_v01":"17.00","half_v03":"26.00","half_v10":"14.00","half_v11":"5.80","half_v13":"3.85","half_v30":"67.00","half_v31":"17.00","half_v33":"1.80"},{"unsupport":"3006_01,3010_01","homeRank":"24","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"解放者杯","teamId":"006","weekId":"4","matchDate":"2018-04-27 08:30:00","awayRank":"8","day":"20180426","homeTeam":"佩纳罗尔","week":"周四","guestTeam":"亚自由","ishot":0,"supportAwards":"","v0":"4.45","v1":"3.20","v3":"1.69","letVs_v0":"1.94","letVs_v1":"3.20","letVs_v3":"3.32","letVs_letPoint":"-1","score_v00":"7.25","score_v01":"9.50","score_v02":"21.00","score_v03":"60.00","score_v04":"250.0","score_v05":"700.0","score_v09":"250.0","score_v10":"5.50","score_v11":"6.20","score_v12":"13.00","score_v13":"40.00","score_v14":"150.0","score_v15":"600.0","score_v20":"6.80","score_v21":"7.00","score_v22":"17.00","score_v23":"50.00","score_v24":"250.0","score_v25":"700.0","score_v30":"12.50","score_v31":"14.00","score_v32":"29.00","score_v33":"80.00","score_v40":"30.00","score_v41":"40.00","score_v42":"70.00","score_v50":"80.00","score_v51":"100.0","score_v52":"250.0","score_v90":"70.00","score_v99":"600.0","goal_v0":"7.25","goal_v1":"3.50","goal_v2":"3.00","goal_v3":"3.85","goal_v4":"6.50","goal_v5":"13.00","goal_v6":"25.00","goal_v7":"38.00","half_v00":"7.75","half_v01":"15.00","half_v03":"29.00","half_v10":"9.50","half_v11":"4.60","half_v13":"3.75","half_v30":"50.00","half_v31":"15.00","half_v33":"2.45"},{"unsupport":"3006_01,3010_01","homeRank":"27","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"解放者杯","teamId":"007","weekId":"4","matchDate":"2018-04-27 08:30:00","awayRank":"7","day":"20180426","homeTeam":"达伽马","week":"周四","guestTeam":"竞技","ishot":0,"supportAwards":"","v0":"2.16","v1":"3.05","v3":"2.95","letVs_v0":"4.87","letVs_v1":"3.75","letVs_v3":"1.52","letVs_letPoint":"+1","score_v00":"8.25","score_v01":"7.00","score_v02":"9.50","score_v03":"20.00","score_v04":"50.00","score_v05":"150.0","score_v09":"80.00","score_v10":"8.50","score_v11":"6.00","score_v12":"7.50","score_v13":"18.00","score_v14":"40.00","score_v15":"120.0","score_v20":"14.00","score_v21":"9.50","score_v22":"12.50","score_v23":"25.00","score_v24":"70.00","score_v25":"250.0","score_v30":"35.00","score_v31":"25.00","score_v32":"30.00","score_v33":"60.00","score_v40":"80.00","score_v41":"70.00","score_v42":"100.0","score_v50":"300.0","score_v51":"250.0","score_v52":"300.0","score_v90":"120.0","score_v99":"400.0","goal_v0":"8.25","goal_v1":"3.85","goal_v2":"3.05","goal_v3":"3.70","goal_v4":"5.80","goal_v5":"12.00","goal_v6":"20.00","goal_v7":"34.00","half_v00":"3.30","half_v01":"13.00","half_v03":"34.00","half_v10":"4.60","half_v11":"4.70","half_v13":"6.50","half_v30":"29.00","half_v31":"13.00","half_v33":"4.80"},{"unsupport":"3006_01,3010_01,3014_02","homeRank":"32","videoLink":"","leagueColor":"","endTime":"04-26 23:52","league":"解放者杯","teamId":"008","weekId":"4","matchDate":"2018-04-27 08:30:00","awayRank":"17","day":"20180426","homeTeam":"巴兰基亚","week":"周四","guestTeam":"利马联盟","ishot":0,"supportAwards":"","v0":"12.50","v1":"5.80","v3":"1.14","letVs_v0":"2.05","letVs_v1":"3.90","letVs_v3":"2.60","letVs_letPoint":"-2","score_v00":"15.00","score_v01":"24.00","score_v02":"60.00","score_v03":"250.0","score_v04":"800.0","score_v05":"1000","score_v09":"400.0","score_v10":"6.50","score_v11":"9.50","score_v12":"28.00","score_v13":"100.0","score_v14":"500.0","score_v15":"1000","score_v20":"5.50","score_v21":"7.50","score_v22":"25.00","score_v23":"80.00","score_v24":"400.0","score_v25":"1000","score_v30":"6.50","score_v31":"9.50","score_v32":"29.00","score_v33":"120.0","score_v40":"10.00","score_v41":"16.00","score_v42":"50.00","score_v50":"20.00","score_v51":"30.00","score_v52":"80.00","score_v90":"16.00","score_v99":"600.0","goal_v0":"15.00","goal_v1":"5.30","goal_v2":"3.65","goal_v3":"3.45","goal_v4":"4.60","goal_v5":"7.50","goal_v6":"12.00","goal_v7":"17.00","half_v00":"21.00","half_v01":"24.00","half_v03":"28.00","half_v10":"26.00","half_v11":"8.00","half_v13":"3.45","half_v30":"80.00","half_v31":"24.00","half_v33":"1.47"}]]
     * leagues : 挪超杯,解放者杯,欧罗巴
     * errorCode : 0000
     * message : 查询成功
     */

    public String leagues;
    public List<List<ResultBean>> result;

    public static class ResultBean implements MultiItemEntity {
        /**
         * unsupport : 3006_01,3010_01
         * homeRank :
         * videoLink :
         * leagueColor :
         * endTime : 04-26 23:52
         * league : 挪超杯
         * teamId : 001
         * weekId : 4
         * matchDate : 2018-04-27 00:30:00
         * awayRank :
         * day : 20180426
         * homeTeam : 利勒斯特
         * week : 周四
         * guestTeam : 罗森博格
         * ishot : 0
         * supportAwards :
         * v0 : 2.03
         * v1 : 3.20
         * v3 : 3.08
         * letVs_v0 : 4.20
         * letVs_v1 : 3.75
         * letVs_v3 : 1.60
         * letVs_letPoint : +1
         * score_v00 : 12.00
         * score_v01 : 8.50
         * score_v02 : 10.00
         * score_v03 : 17.00
         * score_v04 : 32.00
         * score_v05 : 90.00
         * score_v09 : 45.00
         * score_v10 : 10.50
         * score_v11 : 6.00
         * score_v12 : 7.25
         * score_v13 : 13.50
         * score_v14 : 32.00
         * score_v15 : 80.00
         * score_v20 : 17.00
         * score_v21 : 9.75
         * score_v22 : 11.00
         * score_v23 : 22.00
         * score_v24 : 55.00
         * score_v25 : 125.0
         * score_v30 : 36.00
         * score_v31 : 25.00
         * score_v32 : 27.00
         * score_v33 : 50.00
         * score_v40 : 100.0
         * score_v41 : 70.00
         * score_v42 : 70.00
         * score_v50 : 400.0
         * score_v51 : 200.0
         * score_v52 : 300.0
         * score_v90 : 70.00
         * score_v99 : 250.0
         * goal_v0 : 12.00
         * goal_v1 : 5.00
         * goal_v2 : 3.40
         * goal_v3 : 3.45
         * goal_v4 : 5.00
         * goal_v5 : 8.00
         * goal_v6 : 14.00
         * goal_v7 : 20.00
         * half_v00 : 2.80
         * half_v01 : 13.00
         * half_v03 : 34.00
         * half_v10 : 4.90
         * half_v11 : 5.25
         * half_v13 : 7.00
         * half_v30 : 24.00
         * half_v31 : 13.00
         * half_v33 : 5.15
         */

        public String unsupport;
        public String homeRank;
        public String videoLink;
        public String leagueColor;
        public String endTime;
        public String league;
        public String teamId;
        public String weekId;
        public String matchDate;
        public String awayRank;
        public String day;
        public String homeTeam;
        public String week;
        public String guestTeam;
        public int ishot;
        public String supportAwards;
        public String v0;
        public String v1;
        public String v3;
        public boolean v0_sel;
        public boolean v1_sel;
        public boolean v3_sel;
        public String letVs_v0;
        public String letVs_v1;
        public String letVs_v3;
        public boolean letVs_v0_sel;
        public boolean letVs_v1_sel;
        public boolean letVs_v3_sel;
        public String letVs_letPoint;
        public String score_v00;
        public String score_v01;
        public String score_v02;
        public String score_v03;
        public String score_v04;
        public String score_v05;
        public String score_v09;
        public String score_v10;
        public String score_v11;
        public String score_v12;
        public String score_v13;
        public String score_v14;
        public String score_v15;
        public String score_v20;
        public String score_v21;
        public String score_v22;
        public String score_v23;
        public String score_v24;
        public String score_v25;
        public String score_v30;
        public String score_v31;
        public String score_v32;
        public String score_v33;
        public String score_v40;
        public String score_v41;
        public String score_v42;
        public String score_v50;
        public String score_v51;
        public String score_v52;
        public String score_v90;
        public String score_v99;
        public String goal_v0;
        public String goal_v1;
        public String goal_v2;
        public String goal_v3;
        public String goal_v4;
        public String goal_v5;
        public String goal_v6;
        public String goal_v7;
        public String half_v00;
        public String half_v01;
        public String half_v03;
        public String half_v10;
        public String half_v11;
        public String half_v13;
        public String half_v30;
        public String half_v31;
        public String half_v33;

        @Override
        public int getItemType() {
            return JczqExpandAdapter.TYPE_LEVEL_1;
        }
    }
}
