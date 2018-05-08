package top.jplayer.baseprolibrary.ui;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Obl on 2018/1/18.
 * top.jplayer.baseprolibrary.ui
 */

public class TestActivity extends SuperBaseActivity {
    private View content;
    private int mX;
    private int mY;


    // 动画
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float hypot = (float) Math.hypot(content.getHeight(), content.getWidth());
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;

        Animator animator = ViewAnimationUtils.createCircularReveal(
                content, x, y,
                startRadius,
                endRadius);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed)
            animator.addListener(animatorListener);
        return animator;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            content.setVisibility(View.INVISIBLE);
            finish();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(true, mX, mY);
            animator.start();
        } else {
            finish();
        }
    }

    @Override
    protected int initRootLayout() {
        return 0;
    }

    @Override
    public void initRootData(View view) {
        // 让根布局进行动画
        content = view;
        content.post(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mX = getIntent().getIntExtra("x", 0);
                mY = getIntent().getIntExtra("y", 0);
                Animator animator = createRevealAnimator(false, mX, mY);
                animator.start();
            }
        });
        content.setOnTouchListener((v, event) -> {
            v.performClick();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = createRevealAnimator(true, (int) event.getX(), (int) event.getY());
                animator.start();
            } else {
                finish();
            }
            return false;
        });
    }
}