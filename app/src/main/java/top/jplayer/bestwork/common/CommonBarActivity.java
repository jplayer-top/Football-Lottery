package top.jplayer.bestwork.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import top.jplayer.baseprolibrary.ui.SuperBaseActivity;
import top.jplayer.baseprolibrary.utils.ScreenUtils;
import top.jplayer.baseprolibrary.utils.SizeUtils;
import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.bestwork.R;

/**
 * Created by Obl on 2018/4/11.
 * com.ilanchuang.xiaoi.ui
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

@SuppressLint("Registered")
public abstract class CommonBarActivity extends SuperBaseActivity implements BGASwipeBackHelper.Delegate {

    public Toolbar mToolBar;
    public TextView mTvToolTitle;
    public TextView mTvToolRight;
    public ImageView mIvToolRight;
    public ImageView mIvToolRightLeft;
    public ImageView mIvToolLeft;
    public FrameLayout mRootView;
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    public void initRootBundle(Bundle savedInstanceState) {
        super.initRootBundle(savedInstanceState);
        initSwipeBackFinish();
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    @Override
    protected int initRootLayout() {
        return R.layout.activity_common_toolbar;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mToolBar).init();
    }

    @Override
    public void initRootData(View view) {
        super.initRootData(view);
        mToolBar = view.findViewById(R.id.toolbar);
        mIvToolLeft = view.findViewById(R.id.ivToolLeft);
        mIvToolRight = view.findViewById(R.id.ivToolRight);
        mTvToolRight = view.findViewById(R.id.tvToolRight);
        mTvToolTitle = view.findViewById(R.id.tvToolTitle);
        mTvToolTitle.setText(StringUtils.init().fixNullStr(getIntent().getStringExtra("title")));
        mIvToolRightLeft = view.findViewById(R.id.ivToolRightLeft);
        mRootView = view.findViewById(R.id.rootView);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mRootView.getLayoutParams();
        layoutParams.topMargin = toMarginTop();
        mRootView.removeAllViews();
        mRootView.addView(View.inflate(this, initAddLayout(), null));
        initAddView(mRootView);
        initListener();
    }

    private void initListener() {
        mIvToolLeft.setOnClickListener(this::toolLeftBtn);
        mIvToolRight.setOnClickListener(this::toolRightBtn);
        mTvToolRight.setOnClickListener(this::toolRightBtn);
        showRightLeft(View.GONE, null);
    }

    public void toolRightVisiable(View view, Object value) {
        if (view instanceof TextView) {
            mTvToolRight.setVisibility(View.VISIBLE);
            mIvToolRight.setVisibility(View.GONE);
            mTvToolRight.setText((String) value);
        } else if (view instanceof ImageView) {
            mIvToolRight.setVisibility(View.VISIBLE);
            mTvToolRight.setVisibility(View.GONE);
            mIvToolRight.setImageResource((int) value);
        }
    }

    public void showRightLeft(int isVisible, View.OnClickListener listener) {
        mIvToolRightLeft.setVisibility(isVisible);
        mIvToolRightLeft.setOnClickListener(listener);
    }

    public void toolRightBtn(View v) {
    }


    public void toolLeftBtn(View v) {
        finish();
    }

    public int toMarginTop() {
        return SizeUtils.dp2px(56) + ScreenUtils.getStatusBar(this);
    }

    public void initAddView(FrameLayout rootView) {
        initRefreshStatusView(rootView);
    }

    @LayoutRes
    public abstract int initAddLayout();
}
