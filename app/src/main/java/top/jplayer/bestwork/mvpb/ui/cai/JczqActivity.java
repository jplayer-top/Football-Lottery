package top.jplayer.bestwork.mvpb.ui.cai;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.bestwork.R;
import top.jplayer.bestwork.common.CommonBarActivity;
import top.jplayer.bestwork.mvpb.bean.HeaderGameTime;
import top.jplayer.bestwork.mvpb.bean.JczqBean;
import top.jplayer.bestwork.mvpb.presenter.JczqPresenter;
import top.jplayer.bestwork.mvpb.ui.adapter.JczqExpandAdapter;

/**
 * Created by Obl on 2018/4/27.
 * top.jplayer.bestwork.mvpb.ui.cai
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class JczqActivity extends CommonBarActivity {

    private JczqPresenter mPresenter;
    JczqExpandAdapter mAdapter;
    ArrayList<MultiItemEntity> list;
    //记录需扩展位置
    private ArrayList<Integer> expandPos;
    private int curPos = 0;
    private TextView mTvTime;
    private TextView mTvCountGame;
    private ImageView mIv;
    private View mLlHeader;
    public List<Integer> recordHeader;
    private int recordPosition = -1;

    @Override

    public int initAddLayout() {
        return R.layout.activity_jczq;
    }

    @Override
    public void refreshStart() {
        super.refreshStart();
        mPresenter.reqJcaqBean();
    }

    @Override
    public void initAddView(FrameLayout rootView) {
        super.initAddView(rootView);
        mPresenter = new JczqPresenter(this);
        mMultipleStatusView.showLoading();
        mPresenter.reqJcaqBean();
        mAdapter = new JczqExpandAdapter(list, this);
        expandPos = new ArrayList<>();
        mRecyclerView.setAdapter(mAdapter);

        mTvTime = rootView.findViewById(R.id.tvTime);
        mTvCountGame = rootView.findViewById(R.id.tvCountGame);
        mIv = rootView.findViewById(R.id.iv);
        mLlHeader = rootView.findViewById(R.id.llHeader);
        recordHeader = new ArrayList<>();
        // important! setLayoutManager should be called after setAdapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearManager.findFirstVisibleItemPosition();
                MultiItemEntity entity = mAdapter.getData().get(position);
                if (entity instanceof HeaderGameTime) {
                    recordPosition = position;
                    initFakeHeader(position, entity);
                } else {
                    lastPos = 0;
                    for (Integer integer : recordHeader) {
                        if (integer > position) {
                            lastPos = recordHeader.indexOf(integer);
                            break;
                        }
                    }
                    if (recordPosition > 0 && recordPosition > position) {
                        initFakeHeader(lastPos - 1, mAdapter.getData().get(lastPos - 1));
                    }
                }
            }
        });
    }

    int lastPos = 0;

    private void initFakeHeader(int position, MultiItemEntity entity) {
        if (entity instanceof HeaderGameTime) {
            HeaderGameTime entityHeader = (HeaderGameTime) entity;
            mTvTime.setText(entityHeader.tvTime);
            mTvCountGame.setText(entityHeader.tvCountGame);
            ViewAnimator.animate(mIv).rotation(entityHeader.isExpanded() ? 90 : 270).duration(500).start();
            mLlHeader.setOnClickListener(v -> {
                if (entityHeader.isExpanded()) {
                    mAdapter.collapse(position);
                } else {
                    mAdapter.expand(position);
                }
                resetRecord();
                mRecyclerView.scrollToPosition(position);
            });
        }

    }

    public void repJczqBean(JczqBean jczqBean) {
        if (jczqBean != null && jczqBean.result.size() > 0) {
            List<List<JczqBean.ResultBean>> result = jczqBean.result;
            showContent();
            curPos = 0;
            list = generateData(result);
            mAdapter.setNewData(list);
            recordHeader.addAll(expandPos);
            Observable.fromIterable(expandPos).subscribe(integer -> mAdapter.expand(integer));
        } else {
            showEmpty();
        }

    }


    private ArrayList<MultiItemEntity> generateData(List<List<JczqBean.ResultBean>> jczqBean) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        expandPos.clear();
        Observable.fromIterable(jczqBean).subscribe(resultBeans -> {
            JczqBean.ResultBean resultBean = resultBeans.get(0);
            String time = String.format(Locale.CHINA, "%s   %s", resultBean.matchDate.substring(0, 10), resultBean
                    .week);
            String countGame = String.format(Locale.CHINA, "%d场比赛", resultBeans.size());
            HeaderGameTime headerGameTime = new HeaderGameTime(time, countGame);
            res.add(headerGameTime);
            expandPos.add(curPos);
            curPos = resultBeans.size() + 1 + curPos;
            Observable.fromIterable(resultBeans).subscribe(headerGameTime::addSubItem);
        });
        return res;
    }

    public void resetRecord() {
        List<MultiItemEntity> data = mAdapter.getData();
        recordHeader.clear();
        for (MultiItemEntity entity : data) {
            if (entity instanceof HeaderGameTime) {
                recordHeader.add(data.indexOf(entity));
            }
        }
        LogUtil.e(recordHeader);
    }
}
