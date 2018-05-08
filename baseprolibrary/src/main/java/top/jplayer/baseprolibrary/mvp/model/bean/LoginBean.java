package top.jplayer.baseprolibrary.mvp.model.bean;

/**
 * Created by Obl on 2018/1/17.
 * top.jplayer.baseprolibrary.mvp.model.bean
 */

public class LoginBean {


    /**
     * errorCode : 0000
     * message : 登录成功
     * result : {"userName":"17667936541","userNo":"2017082407616512","name":"刘福龙","certId":"370923196408041995","nickName":"红染坊","mobileId":"17667936541","hasPayPwd":"1","accessToken":"da07391e62c194bc673158dc3be534ae","userAccountBean":{"mobileId":"17667936541","hasPayPwd":"1"}}
     */

    public String errorCode;
    public String message;
    public ResultBean result;

    public static class ResultBean {
        /**
         * userName : 17667936541
         * userNo : 2017082407616512
         * name : 刘福龙
         * certId : 370923196408041995
         * nickName : 红染坊
         * mobileId : 17667936541
         * hasPayPwd : 1
         * accessToken : da07391e62c194bc673158dc3be534ae
         * userAccountBean : {"mobileId":"17667936541","hasPayPwd":"1"}
         */

        public String userName;
        public String userNo = "";
        public String name = "";
        public String certId;
        public String nickName;
        public String mobileId;
        public String hasPayPwd;
        public String accessToken;
        public UserAccountBeanBean userAccountBean;

        public static class UserAccountBeanBean {
            /**
             * mobileId : 17667936541
             * hasPayPwd : 1
             */

            public String mobileId;
            public String hasPayPwd;
        }
    }
}
