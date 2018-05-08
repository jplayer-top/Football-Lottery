package top.jplayer.baseprolibrary.widgets.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;

/**
 * Created by Obl on 2018/3/20.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public abstract class BaseCustomDialogFragment extends DialogFragment {

    public View mContentView;
    protected ImmersionBar mImmersionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.dialog_custom);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(contentView());
        return dialog;
    }

    private View contentView() {
        mContentView = LayoutInflater.from(getContext()).inflate(initLayout(), null);
        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setGravity(setGravity());  //此处可以设置dialog显示的位置
            window.setWindowAnimations(setAnim());  //添加动画
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            layoutParams.dimAmount = setAlpha();
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(setWidth(), setHeight());
            window.setAttributes(layoutParams);
            window.setSoftInputMode(setSoftInputState());
        }
        initView(mContentView);
        initData(mContentView);
        initImmersionBar();
    }

    public void initData(View view) {

    }

    public float setAlpha() {
        return 0.5f;
    }

    private int setWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    public int setHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public int setAnim() {
        return R.style.AnimBottom;
    }

    public int setGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        KeyboardUtils.init().hideSoftInput(getActivity());
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        if (isImmersionBarEnabled()) {
            mImmersionBar = ImmersionBar
                    .with(this, getDialog());
            mImmersionBar.init();
        }
    }

    protected abstract void initView(View view);

    public abstract @LayoutRes
    int initLayout();

    public int setSoftInputState() {
        return WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN;
    }

    /**
     * 手动打开软键盘
     */
    public void setSoftInputMethod() {
        KeyboardUtils.init().showInputMethod(getActivity());
    }
}


