package top.jplayer.baseprolibrary.utils;

import android.support.annotation.StringDef;

/**
 * Created by Obl on 2017/10/17.
 * com.ilanchuang.xiaoi.base
 */

public class Const {
    public static final String TYPE_SINGLE = "1";
    public static final String TYPE_GROUP = "2";

    @StringDef({TYPE_SINGLE, TYPE_GROUP})
    public @interface Type {
    }
}
