package top.jplayer.baseprolibrary.widgets.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.github.florent37.viewanimator.AnimationBuilder;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.net.IoMainSchedule;


/**
 * Created by vondear on 2016/7/19.
 * Mainly used for confirmation and cancel.
 */
public class DialogRedHb extends BaseCustomDialog {

    public DialogRedHb(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> cancel());
    }


    @Override
    public int initLayout() {
        return R.layout.dialog_redhb;
    }

    /**
     * 无限循环金币动画
     */
    @NonNull
    public Disposable looperAnim(View v1) {
        AnimationBuilder animationBuilder = ViewAnimator
                .animate(v1)
                .duration(2000)
                .rotationY(0,
                        30, 60, 90, 120,
                        150, 180, 210, 240,
                        270, 300, 330, 360);
        animationBuilder.start();
        return Observable.interval(2000, TimeUnit.MILLISECONDS)
                .compose(new IoMainSchedule<>())
                .subscribe(aLong -> animationBuilder.start());
    }
}
