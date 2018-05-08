package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2017/3/16.
 * 加载中
 */

public class DialogLoading extends BaseCustomDialog {


    public DialogLoading(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_loading_spinkit;
    }

    @Override
    public int setWidth(int i) {
        return super.setWidth(5);
    }

}
