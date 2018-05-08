package top.jplayer.baseprolibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.github.florent37.viewanimator.ViewAnimator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/2
 *     desc  : 键盘相关的工具类
 * </pre>
 */
public class KeyboardUtils {
    private static KeyboardUtils mKeyboardUtils;

    public static KeyboardUtils init() {
        if (mKeyboardUtils == null) {
            mKeyboardUtils = new KeyboardUtils();
        }
        return mKeyboardUtils;
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null && imm != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public void showInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null && imm != null) {
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }


    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
     */
    public boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public boolean checkKeyboard(Activity activity, View v) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0);
            return true;
            //软键盘已弹出
        } else {
            return false;
            //软键盘未弹出
        }
    }

    /**
     * 点击空白处，延时三百毫秒关闭键盘，避免 多个EditText 在同一个界面，出现的键盘闪烁问题
     */
    public void clickBound2CloseInput(Activity activity, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Observable.timer(300, TimeUnit.MILLISECONDS).subscribe(aLong -> {
                View v = activity.getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    hideSoftInput(activity);
                }
            });

        }
    }

    public void fixKeyBorder(View viewMove, View viewStationary, View container) {
        int height = ScreenUtils.getScreenHeight();
        viewMove.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) ->
        {
            if (height - bottom > height * 0.3f) {
                LogUtil.e("-----up ----");
                float staY = viewStationary.getY();
                float moveY = viewMove.getY();
                float scroll = moveY - staY;
                int fix = SizeUtils.dp2px(20);
                if (scroll < fix) {
                    ViewAnimator.animate(container).translationY(scroll - fix).duration(200).start();
                }
            } else {
                LogUtil.e("-----down ----");
                ViewAnimator.animate(container).dp().translationY(0).duration(200).start();
            }
        });
    }
}