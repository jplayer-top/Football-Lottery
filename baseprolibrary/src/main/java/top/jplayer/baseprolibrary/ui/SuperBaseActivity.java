package top.jplayer.baseprolibrary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.contract.IContract;
import top.jplayer.baseprolibrary.utils.KeyboardUtils;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.baseprolibrary.utils.ToastUtils;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Obl on 2018/1/9.
 * top.jplayer.baseprolibrary.ui
 */

public abstract class SuperBaseActivity extends AppCompatActivity implements IContract.IView {

    public ImmersionBar mImmersionBar;
    public View superRootView;
    public Activity mActivity;
    public Bundle mBundle;
    public SmartRefreshLayout mSmartRefreshLayout;
    public MultipleStatusView mMultipleStatusView;
    public RecyclerView mRecyclerView;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRootBundle(savedInstanceState);
        superRootView = initRootView();
        mActivity = this;
        setContentView(superRootView);
        initRootData(superRootView);
        initInject(superRootView);
        initImmersionBar();
        initSaveInstanceState(savedInstanceState);
    }

    protected void initSaveInstanceState(@Nullable Bundle savedInstanceState) {
    }

    protected void initImmersionBar() {
        if (isImmersionBarEnabled()) {
            //在BaseActivity里初始化
            mImmersionBar = ImmersionBar.with(this)
                    //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                    .statusBarDarkFont(true, 0.2f);
            mImmersionBar.init();
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected View initRootView() {
        int layout = initRootLayout();
        if (layout == 0) {
            layout = R.layout.layout_test;
        }
        return View.inflate(this, layout, null);
    }

    /**
     * 刷新+多状态
     *
     * @param view
     */
    public void initRefreshStatusView(View view) {
        mSmartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        mMultipleStatusView = view.findViewById(R.id.multipleStatusView);
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.setOnRefreshListener(refresh -> refreshStart());
        }
        mRecyclerView = view.findViewById(R.id.recyclerView);
        initRootRecyclerView();

    }

    private void initRootRecyclerView() {
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }

    public void refreshStart() {

    }

    /**
     * 依赖注入
     */
    public void initInject(View view) {
    }

    @LayoutRes
    protected abstract int initRootLayout();

    /**
     * 默认原始根布局下的FrameLayout,基于相同ToolBar 的视图
     *
     * @param view 根布局
     */
    public void initRootData(View view) {
        initRefreshStatusView(view);
    }

    /**
     * 保存状态,可设置一些公共代码
     *
     * @param savedInstanceState 所保存的状态信息
     */
    public void initRootBundle(Bundle savedInstanceState) {
        mBundle = getIntent().getBundleExtra("bundle");
    }

    private int doubleBack = 0;
    public boolean isOpenDoubleBack = false;

    @Override
    public void onBackPressed() {
        if (isOpenDoubleBack) {
            if (doubleBack > 1) {
                super.onBackPressed();
            } else {
                ToastUtils.init().showQuickToast("再按一次退出应用");
            }
            Observable.timer(500, TimeUnit.MICROSECONDS).subscribe(aLong -> ++doubleBack);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 设置文字
     *
     * @param viewResId
     * @param text
     */
    public void setText(@IdRes int viewResId, CharSequence text) {
        TextView tv = findViewById(viewResId);
        tv.setText(StringUtils.init().fixNullStr(text));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        saveInstanceState(outState, outPersistentState);
    }

    public void saveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreInstanceState(savedInstanceState);
    }

    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }
    }

    /**
     * 是否检查关闭软键盘
     */
    protected boolean isCheckKeyboard = true;

    /**
     * 点击空白处关闭软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isCheckKeyboard)
            KeyboardUtils.init().clickBound2CloseInput(this, ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void showError() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showError();
        }
        autoRefreshFinish();
    }

    @Override
    public void showLoading() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showLoading();
        }
    }

    @Override
    public void showEmpty() {
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showEmpty();
        }
        autoRefreshFinish();
    }

    public void showContent() {
        autoRefreshFinish();
        if (mMultipleStatusView != null) {
            mMultipleStatusView.showContent();
        }
    }

    public void autoRefreshFinish() {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
    }
}
