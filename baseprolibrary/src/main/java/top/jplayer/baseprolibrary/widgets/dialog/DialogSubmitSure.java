package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.view.View;

import top.jplayer.baseprolibrary.R;


/**
 * Created by vondear on 2016/7/19.
 * Mainly used for confirmation and cancel.
 */
public class DialogSubmitSure extends BaseCustomDialog {

    public DialogSubmitSure(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {

    }




    @Override
    public int initLayout() {
        return R.layout.dialog_sure;
    }

}
