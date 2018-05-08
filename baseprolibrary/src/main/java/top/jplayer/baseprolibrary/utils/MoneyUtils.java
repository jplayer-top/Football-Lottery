package top.jplayer.baseprolibrary.utils;

import java.math.BigDecimal;

/**
 * Created by Obl on 2018/2/8.
 * top.jplayer.baseprolibrary.utils
 */

public class MoneyUtils {
    /**
     * 0-->0.00 99 -->0.99 201 -->2.01 金额格式化 （分）---> (元)
     *
     * @param num
     * @return
     */
    public static String formatIntF(int num) {
        boolean l = true;
        if (num < 0) {
            l = false;
            num = Math.abs(num);
        }
        if (num == 0) {
            return "0.00";
        }
        if (num < 10) {
            String temp = "0.0" + num;
            return l ? temp : "-" + temp;
        }
        if (num < 100) {
            String temp = "0." + num;
            return l ? temp : "-" + temp;
        }
        String temp = "";
        temp += num / 100;
        temp += ".";
        int v = num % 100;
        if (v < 10) {
            temp += "0";
        }
        temp += v;
        return l ? temp : "-" + temp;
    }

    /**
     * 金额解析 （元）---> (分)
     *
     * @param num
     * @return
     */
    public static int parseIntF(String num) {
        if (num == null) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(num);
        bd = bd.movePointRight(2);
        return bd.intValue();
    }

}
