package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.view.View;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogNoviceGuide extends BaseCustomDialog {

    public DialogNoviceGuide(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> cancel());
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
    public int initLayout() {
        return R.layout.dialog_novice_guide;
    }
}
