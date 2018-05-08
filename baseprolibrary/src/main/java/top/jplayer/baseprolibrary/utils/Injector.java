package top.jplayer.baseprolibrary.utils;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PEO on 2017/4/10.
 * 注解器
 */

public final class Injector {
    public final static float[] BT_SELECTED = new float[]{1, 0, 0, 0, -50, 0, 1,
            0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0};
    public final static float[] BT_NOT_SELECTED = new float[]{1, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0};

    public final static ColorMatrixColorFilter COLOR_FILTER = new ColorMatrixColorFilter(BT_SELECTED);
    public final static ColorMatrixColorFilter COLOR_FILTER_UN = new ColorMatrixColorFilter(BT_NOT_SELECTED);

    public static void makeImageClickEffect(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setOnTouchListener((v, event) -> {
            ImageView imageView1 = (ImageView) v;
            if (imageView1.getDrawable() == null) {
                return false;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                imageView1.setColorFilter(COLOR_FILTER);
            } else {
                imageView1.setColorFilter(COLOR_FILTER_UN);
            }
            return false;
        });
    }

    public static void makeImageClickNotEffect(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        imageView.setOnTouchListener((v, event) -> {
            ImageView imageView1 = (ImageView) v;
            imageView1.performClick();
            if (imageView1.getDrawable() == null) {
                return false;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                imageView1.setColorFilter(COLOR_FILTER_UN);
            } else {
                imageView1.setColorFilter(COLOR_FILTER_UN);
            }
            return false;
        });
    }

    public static void str2TextView(TextView textView, String text) {
        if (textView == null)
            return;
        if (text == null)
            text = "";
        textView.setText(text);
    }

    public static void str2TextView(TextView textView, String text, String def) {
        if (textView == null)
            return;
        if (text == null)
            text = def;
        textView.setText(text);
    }

    public static void int2TextView(TextView textView, int text) {
        if (textView == null)
            return;
        textView.setText(String.valueOf(text));
    }

    public static void fdstr2TextView(TextView textView, String format, String text, String defalut) {
        str2TextView(textView, String.format(format, text), defalut);
    }


    public static void fistr2TextView(TextView textView, String format, int... params) {
        if (textView == null)
            return;
        textView.setText(String.format(format, params));
    }

    public static void fstr2TextView(TextView textView, String format, Object... params) {
        if (textView == null)
            return;
        textView.setText(String.format(format, params));
    }
}
