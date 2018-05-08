package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogLogout extends BaseCustomDialog {

    public DialogLogout(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.btnCancel).setOnClickListener(v -> cancel());
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(8);
    }

    @Override
    public int setAnim() {
        return R.style.AnimFade;
    }

    @Override
    public int setGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_logout;
    }
}
