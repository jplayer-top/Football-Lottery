package top.jplayer.baseprolibrary.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Obl on 2018/3/14.
 * top.jplayer.baseprolibrary.utils
 */

public class StringUtils {
    private static StringUtils mStrUtils;

    public static StringUtils init() {
        if (mStrUtils == null) {
            mStrUtils = new StringUtils();
        }
        return mStrUtils;
    }



    /**
     * 获取十六进制的颜色代码.例如  "#6E36B4" , For HTML ,
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;

        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }

    public boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public boolean isEmpty(CharSequence sequence) {
        return TextUtils.isEmpty(sequence);
    }

    public boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText());
    }

    public String fixNullStr(CharSequence sequence) {
        return fixNullStr(sequence.toString());
    }

    public String fixNullStr(String addStr, String... preStr) {
        StringBuilder aft = new StringBuilder("");
        for (int i = 0; i < preStr.length; i++) {
            if (preStr[i] == null) {
                aft.append("");
            } else {
                aft.append(preStr[i]);
                aft.append(addStr);
            }
        }
        return aft.toString();
    }

    public String fixNullStr(String string) {
        if (isEmpty(string)) {
            return "";
        }
        return string;
    }

    /**
     * 判断是否为空,并返回输入的相应字段
     */
    public String fixNullStr(Object preStr, String midStr, String aftStr) {
        if (preStr == null || preStr.equals("")) {
            return aftStr;
        }
        return preStr.toString() + midStr;
    }

    /**
     * @param preStr 判断的str
     * @param aftStr 如果是空，返回的str
     * @return aftStr
     */
    public String fixNullStr(String preStr, String aftStr) {
        if (isEmpty(preStr)) {
            return aftStr;
        }
        return preStr;
    }


    public void tipEditTextLength(EditText edit, Editable s, int maxLen, String msg) {
        if (s.length() > maxLen) {
            ToastUtils.init().showQuickToast(msg);
            edit.setText(s.subSequence(0, maxLen));
            edit.setSelection(edit.getText().length());
        }
    }
}
