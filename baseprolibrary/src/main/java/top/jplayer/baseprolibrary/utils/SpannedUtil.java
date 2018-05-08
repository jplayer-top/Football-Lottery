package top.jplayer.baseprolibrary.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Obl on 2018/4/19.
 * top.jplayer.baseprolibrary.utils
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class SpannedUtil {
    public static SpannableString getForegroundColorSpan(String str, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return (spannableString);
    }
}
