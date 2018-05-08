package top.jplayer.baseprolibrary.mvp.model.bean;

import java.util.List;

/**
 * Created by Obl on 2018/1/13.
 * top.jplayer.baseprolibrary.mvp.model.bean
 */

public class SampleBean {

    /**
     * errorCode : 0000
     * data : {"list":[{"id":"5a6bdf7d0cf2b32576d01ce5","userName":"好运红包","type":3,"sendTime":"2018-01-27 20:27:00","status":3,"num":200}],"now":"2018-01-27 18:58:16","isTwo":{"hadNum":0,"maxNum":2,"validate":1}}
     */

    public String errorCode;
    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"id":"5a6bdf7d0cf2b32576d01ce5","userName":"好运红包","type":3,"sendTime":"2018-01-27 20:27:00","status":3,"num":200}]
         * now : 2018-01-27 18:58:16
         * isTwo : {"hadNum":0,"maxNum":2,"validate":1}
         */

        public String now;
        public IsTwoBean isTwo;
        public List<ListBean> list;

        public static class IsTwoBean {
            /**
             * hadNum : 0
             * maxNum : 2
             * validate : 1
             */

            public int hadNum;
            public int maxNum;
            public int validate;
        }

        public static class ListBean {
            /**
             * id : 5a6bdf7d0cf2b32576d01ce5
             * userName : 好运红包
             * type : 3
             * sendTime : 2018-01-27 20:27:00
             * status : 3
             * num : 200
             */

            public String id;
            public String userName;
            public int type;
            public String sendTime;
            public int status;
            public int num;
        }
    }
}
