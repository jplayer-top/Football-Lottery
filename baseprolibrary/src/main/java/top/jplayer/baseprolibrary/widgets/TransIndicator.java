package top.jplayer.baseprolibrary.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Obl on 2018/3/30.
 * top.jplayer.baseprolibrary.widgets
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class TransIndicator extends LinearLayout {
    public TransIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public TransIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TransIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        for (int i = 0; i < getChildCount(); i++) {

        }
    }
}
