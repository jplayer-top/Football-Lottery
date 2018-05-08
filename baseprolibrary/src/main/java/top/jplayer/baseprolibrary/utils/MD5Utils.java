package top.jplayer.baseprolibrary.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by PEO on 2017/2/28.
 */

public class MD5Utils {
    public static String getMD5(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            //新建一个字符串，用来获取byte[]数组
            byte[] buffer = str.getBytes();
            //获取以md5格式的MessageDigest
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] newBuffer = digest.digest(str.getBytes());
            //创建一个StringBuffer 用来存放字符
            //增强for循环遍历得到的新数组
            for (byte byt : newBuffer
                    ) {
                //将byte数组转化为int 数；通过  &0xff
                int number = byt & 0Xff;
                //在转化为十六进制字符串，如果是长度为一，将在前边添加一个“0”
                String newStr = Integer.toHexString(number);
                if (newStr.length() <= 1) {
                    sb.append("0");
                }
                sb.append(newStr);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
