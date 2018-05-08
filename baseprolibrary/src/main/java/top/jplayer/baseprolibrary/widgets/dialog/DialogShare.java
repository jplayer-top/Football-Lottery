package top.jplayer.baseprolibrary.widgets.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;

import top.jplayer.baseprolibrary.R;

/**
 * Created by PEO on 2017/2/24.
 * d
 */

public class DialogShare extends BaseCustomDialog {


    public DialogShare(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.tvClose).setOnClickListener(v -> this.dismiss());
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(10);
    }

    @Override
    public int setGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int setAnim() {
        return R.style.AnimBottom;
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_share;
    }
}
