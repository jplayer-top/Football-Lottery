package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogEditBottom extends BaseCustomDialog {

    public DialogEditBottom(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public int setSoftInputState() {
        return WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(10);
    }

    @Override
    public int setAnim() {
        return R.style.AnimBottom;
    }

    @Override
    public int setGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_input_text;
    }
}
