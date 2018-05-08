package top.jplayer.baseprolibrary.ui;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.mvp.presenter.SamplePresenter;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.ui.adapter.SampleAdapter;
import top.jplayer.baseprolibrary.utils.DateUtils;
import top.jplayer.baseprolibrary.utils.LogUtil;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.widgets.MultipleStatusView;

/**
 * Created by Administrator on 2018/1/27.
 * 样例
 */

public class SampleActivity extends SuperBaseActivity implements SampleContract.ISampleView {

    private SamplePresenter presenter;
    private SampleAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private MultipleStatusView multipleStatusView;
    private EditText etPhone;
    private EditText etPassword;
    private LinearLayout llNames;
    @Override
    protected int initRootLayout() {
        return  R.layout.activity_sample;
    }

    @Override
    public void initRootData(View view) {
        presenter = new SamplePresenter(this);
        refreshLayout = view.findViewById(R.id.smartRefreshLayout);
        multipleStatusView = view.findViewById(R.id.multipleStatusView);
        etPhone = view.findViewById(R.id.etPhone);
        etPassword = view.findViewById(R.id.etPassword);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        showLoading();
        presenter.requestHBList();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        ArrayList<SampleBean.DataBean.ListBean> sampleBeans = new ArrayList<>();
        adapter = new SampleAdapter(sampleBeans);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((thisAdapter, v, position) ->
        {
            List<SampleBean.DataBean.ListBean> datas = thisAdapter.getData();
            SampleBean.DataBean.ListBean listBean = datas.get(position);
            getUserNo(listBean.id);
            return false;
        });
        refreshLayout.setOnRefreshListener(refresh -> presenter.requestHBList());
        btnAdd.setOnClickListener(v ->
                presenter.addAccount(etPhone.getText().toString().trim(),
                        etPassword.getText().toString().trim()));
        llNames = view.findViewById(R.id.llShowName);
        getNames();
    }


    private void getNames() {
        String names = (String) SharePreUtil.getData(this, "name", "");
        String userNos = (String) SharePreUtil.getData(this, "userNo", "");
        LogUtil.e(names);
        LogUtil.e(userNos);
        if (!TextUtils.equals("", names)) {
            assert names != null;
            Observable.fromIterable(Arrays.asList(names.split(",")))
                    .compose(new IoMainSchedule<>())
                    .subscribe(s -> {
                        TextView textView = new TextView(this);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                        textView.setGravity(Gravity.CENTER);
                        textView.setText(String.format(Locale.CHINA, "%s-已登录", s));
                        textView.setTextColor(getResources().getColor(R.color.grey));
                        llNames.addView(textView);
                    });
        }
    }

    private void getUserNo(String id) {
        String userNos = (String) SharePreUtil.getData(this, "userNo", "");
        LogUtil.e(userNos);
        if (!TextUtils.equals("", userNos)) {
            assert userNos != null;
            Observable.fromIterable(Arrays.asList(userNos.split(",")))
                    .compose(new IoMainSchedule<>())
                    .subscribe(s ->
                            presenter.requestGrad(id, s)
                    );
        }
    }

    @Override
    public void setHBList(SampleBean sampleBean) {
        multipleStatusView.showContent();
        SampleBean.DataBean data = sampleBean.data;
        refreshLayout.finishRefresh();
        adapter.setNewData(data.list);

        autoGrad(data);
    }

    /**
     * 自动抢
     *
     * @param data
     */
    private void autoGrad(SampleBean.DataBean data) {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        String sendTime = data.list.get(0).sendTime;
        long stamp = DateUtils.dateToStamp(sendTime);
        long time = new Date().getTime();
        long delay = (stamp - time) - 7000;
        String id = data.list.get(0).id;
        Disposable disposable = Observable.timer(delay, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> getUserNo(id));
        compositeDisposable.add(disposable);
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showError() {

        if (multipleStatusView != null) {
            multipleStatusView.showError();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void showLoading() {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
    }

    @Override
    public void showEmpty() {

        if (multipleStatusView != null) {
            multipleStatusView.showEmpty();
        }
        refreshLayout.finishRefresh();

    }


    public void loginSuccess() {
        llNames.removeAllViews();
        getNames();
    }


}
